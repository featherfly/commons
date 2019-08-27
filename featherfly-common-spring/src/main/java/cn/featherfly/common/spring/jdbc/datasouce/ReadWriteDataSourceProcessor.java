package cn.featherfly.common.spring.jdbc.datasouce;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.CompositeTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

import cn.featherfly.common.lang.CollectionUtils;

/**
 * 
 * 
 * <pre>
 * 
 * 此类实现了通过AOP切面实现读/写选择：
 *   
 *   
 * ★★读/写动态数据库选择处理器★★
 * 1、首先读取&lt;tx:advice&gt;事务属性配置
 * 
 * 2、对于所有读方法设置 read-only="true" 表示读取操作（以此来判断是选择读还是写库），其他操作都是走写库
 *    如&lt;tx:method name="×××" read-only="true"/&gt;
 *    
 * 3、 forceChoiceReadWhenWrite用于确定在如果目前是写（即开启了事务），下一步如果是读，
 *    是直接参与到写库进行读，还是强制从读库读
 *      forceChoiceReadWhenWrite:false 表示目前是写，下一步如果是读，强制参与到写事务（即从写库读）
 *                                  这样可以避免写的时候从读库读不到数据
 *                                  
 *                                  通过设置事务传播行为：SUPPORTS实现
 *                                  
 *      forceChoiceReadWhenWrite:true 表示不管当前事务是写/读，都强制从读库获取数据
 *                                  通过设置事务传播行为：NOT_SUPPORTS实现（连接是尽快释放）                
 *                                  『此处借助了 NOT_SUPPORTS会挂起之前的事务进行操作 然后再恢复之前事务完成的』
 * 4、配置方式
 *  &lt;bean id="readWriteDataSourceTransactionProcessor" class=
"cn.javass.common.datasource.ReadWriteDataSourceProcessor"&gt;
 *      &lt;property name="forceChoiceReadWhenWrite" value="false"/&gt;
 *  &lt;/bean&gt;
 *
 * 5、支持&lt;tx:advice&gt;和@Transactional注解事务
 *  
 *  
 *  
 * ★★通过AOP切面实现读/写库选择★★
 * 
 * 1、首先将当前方法 与 根据之前【读/写动态数据库选择处理器】  提取的读库方法 进行匹配
 * 
 * 2、如果匹配，说明是读取数据：
 *  2.1、如果forceChoiceReadOnWrite:true，即强制走读库
 *  2.2、如果之前是写操作且forceChoiceReadOnWrite:false，将从写库进行读取
 *  2.3、否则，到读库进行读取数据
 * 
 * 3、如果不匹配，说明默认将使用写库进行操作
 * 
 * 4、配置方式
 *      &lt;aop:aspect order="-2147483648" ref=
"readWriteDataSourceTransactionProcessor"&gt;
 *          &lt;aop:around pointcut-ref="txPointcut" method=
"determineReadOrWriteDB"/&gt;
 *      &lt;/aop:aspect&gt;
 *  4.1、此处order = Integer.MIN_VALUE 即最高的优先级（请参考http://jinnianshilongnian.iteye.com/blog/1423489）
 *  4.2、切入点：txPointcut 和 实施事务的切入点一样
 *  4.3、determineReadOrWriteDB方法用于决策是走读/写库的，请参考
 *       &#64;see ReadWriteDataSourceDecision
 *       &#64;see ReadWriteDataSource
 * 
 * </pre>
 * 
 * @author zhongj
 */
public class ReadWriteDataSourceProcessor implements ApplicationContextAware {

    /**
     */
    public ReadWriteDataSourceProcessor() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(ReadWriteDataSourceProcessor.class);

    private boolean forceChoiceReadWhenWrite;

    private TransactionAttributeSource transactionAttributeSource;

    /**
     * 当之前操作是写的时候，是否强制从从库读 默认（false） 当之前操作是写，默认强制从写库读
     * 
     * @param forceChoiceReadWhenWrite
     *            forceChoiceReadWhenWrite
     */
    public void setForceChoiceReadWhenWrite(boolean forceChoiceReadWhenWrite) {
        this.forceChoiceReadWhenWrite = forceChoiceReadWhenWrite;
    }

    /**
     * <p>
     * 确定当前使用写库还是读库
     * </p>
     * 
     * @param pjp
     *            ProceedingJoinPoint
     * @return Object
     * @throws Throwable
     *             Throwable
     */
    public Object determineReadOrWriteDB(ProceedingJoinPoint pjp) throws Throwable {
        if (pjp.getSignature() instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
           
            final RuleBasedTransactionAttribute txAttr = (RuleBasedTransactionAttribute) transactionAttributeSource
                    .getTransactionAttribute(methodSignature.getMethod(), pjp.getSignature().getDeclaringType());

            if (txAttr == null) {
                throw new ReadWriteDataSourceTransactionException(
                        "there is no transaction for " + methodSignature.getMethod());
            }
            
            LOG.debug("{} with TransactionAttribute {}", methodSignature.getMethod().toString(), txAttr.toString());
            
            boolean choice = false;
            if (ReadWriteDataSourceDecision.isChoiceNone()) {
                // 表示第一个标记，事物可能嵌套，所有只有第一个标记的才才能进行清除
                choice = true;
            }
            if (isChoiceReadDB(txAttr)) {
                ReadWriteDataSourceDecision.markRead();
                LOG.debug("read transaction process for {}", methodSignature.getMethod().toString());
            } else {
                ReadWriteDataSourceDecision.markWrite();
                LOG.debug("write transaction process for {}", methodSignature.getMethod().toString());
            }

            try {
                return pjp.proceed();
            } finally {
                if (choice) {
                    ReadWriteDataSourceDecision.reset();
                }
            }
        }

        throw new ReadWriteDataSourceTransactionException("! pjp.getSignature() instanceof MethodSignature");
    }
    
    private boolean isChoiceReadDB(RuleBasedTransactionAttribute txAttr) {
        LOG.debug("ReadWriteDataSourceDecision.isChoiceNone {}", ReadWriteDataSourceDecision.isChoiceNone());
        if (ReadWriteDataSourceDecision.isChoiceNone()) {
            // 没有标记读写，使用第一个调用的事务确定读写
            return txAttr.isReadOnly();
        } else {
            // 嵌套调用,已经确定最外层的事务了
            if (forceChoiceReadWhenWrite) {
                // 不管之前操作是读还是写，默认强制从读库读 （设置为NOT_SUPPORTED即可）
                if (txAttr.isReadOnly()) {
                    txAttr.setPropagationBehavior(Propagation.NOT_SUPPORTED.value());
                }
                return true;
            } else if (ReadWriteDataSourceDecision.isChoiceWrite()) {
                // 如果之前选择了写库 现在还选择 写库
                txAttr.setPropagationBehavior(Propagation.SUPPORTS.value());
                return false;
            } else {
                return txAttr.isReadOnly();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Collection<TransactionAspectSupport> ts = applicationContext.getBeansOfType(TransactionAspectSupport.class)
                .values();

        List<TransactionAttributeSource> tas = new ArrayList<>();
        for (TransactionAspectSupport t : ts) {
            tas.add(t.getTransactionAttributeSource());
        }

        if (tas.size() == 1) {
            this.transactionAttributeSource = tas.get(0);
        } else if (ts.size() > 1) {
            transactionAttributeSource = new CompositeTransactionAttributeSource(
                    CollectionUtils.toArray(tas, TransactionAttributeSource.class));
        }
    }
}
