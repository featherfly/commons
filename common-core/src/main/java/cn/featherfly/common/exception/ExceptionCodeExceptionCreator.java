package cn.featherfly.common.exception;

import java.util.Locale;
import java.util.function.BiFunction;

import cn.featherfly.common.function.ThFunction;
import cn.featherfly.common.tuple.Tuple;

/**
 * The type ExceptionCodeExceptionCreator.
 *
 * @author zhongj
 * @param <T> the params generic type
 * @param <E> the ExceptionCodeException generic type
 */
public class ExceptionCodeExceptionCreator<T, E extends ExceptionCodeException> extends SimpleExceptionCode {

    private BiFunction<ExceptionCode, Object[], E> exceptionCreator;

    private ThFunction<ExceptionCode, Object[], Locale, E> exceptionCreator2;

    /**
     * Instantiates a new exception code exception creator.
     *
     * @param module the module
     * @param num the num
     * @param message the message
     * @param exceptionCreator the exception creator
     * @param exceptionCreator2 the exception creator 3
     */
    public ExceptionCodeExceptionCreator(String module, Integer num, String message,
        BiFunction<ExceptionCode, Object[], E> exceptionCreator,
        ThFunction<ExceptionCode, Object[], Locale, E> exceptionCreator2) {
        super(module, num, message);
        this.exceptionCreator = exceptionCreator;
        this.exceptionCreator2 = exceptionCreator2;
    }

    /**
     * Creates the exception.
     *
     * @param params the params
     * @return the e
     */
    public E create(T params) {
        if (params instanceof Tuple) {
            return exceptionCreator.apply(this, ((Tuple) params).stream().toArray());
        }
        return exceptionCreator.apply(this, new Object[] { params });
    }

    /**
     * Creates the exception.
     *
     * @param params the params
     * @param locale the locale
     * @return the e
     */
    public E create(T params, Locale locale) {
        if (params instanceof Tuple) {
            return exceptionCreator2.apply(this, ((Tuple) params).stream().toArray(), locale);
        }
        return exceptionCreator2.apply(this, new Object[] { params }, locale);
    }
}
