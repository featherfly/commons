package cn.featherfly.common.ast;

import javax.annotation.Nonnull;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedOptions;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

/**
 * The Class AbstractProcessor.
 */
@SupportedOptions("log")
public abstract class JavacProcessor extends javax.annotation.processing.AbstractProcessor {

    /** The messager. */
    private Messager messager;

    /** The trees. */
    protected JavacTrees trees;

    /** The tree maker. */
    protected TreeMaker treeMaker;

    /** The names. */
    protected Names names;

    /** The element utils. */
    protected JavacElements elementUtils;

    /** The javac. */
    protected Javac javac;

    protected Logger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void init(@Nonnull ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
        elementUtils = (JavacElements) processingEnv.getElementUtils();

        int logLevel = 0;
        String log = processingEnv.getOptions().get("log");
        if (log != null) {
            if (log.equalsIgnoreCase("debug")) {
                logLevel = 4;
            } else if (log.equalsIgnoreCase("info")) {
                logLevel = 3;
            } else if (log.equalsIgnoreCase("warning")) {
                logLevel = 2;
            } else if (log.equalsIgnoreCase("error")) {
                logLevel = 1;
            }
        }

        logger = new Logger(this.getClass().getSimpleName(), messager, logLevel);

        javac = new Javac(treeMaker, trees, names, logger);

        doInit(processingEnv);
    }

    /**
     * Do init.
     *
     * @param processingEnv the processing env
     */
    protected abstract void doInit(@Nonnull ProcessingEnvironment processingEnv);

    protected void debug(String message) {
        logger.debug(message);
    }

    protected void info(String message) {
        logger.info(message);
    }

    protected void warning(String message) {
        logger.warning(message);
    }

    protected void error(String message) {
        logger.error(message);
    }
}