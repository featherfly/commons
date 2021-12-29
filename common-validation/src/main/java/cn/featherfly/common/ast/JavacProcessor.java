package cn.featherfly.common.ast;

import javax.annotation.Nonnull;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

/**
 * The Class AbstractProcessor.
 */
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

        javac = new Javac(treeMaker, trees, names, messager, processingEnv);
    }

    /**
     * Do init.
     *
     * @param processingEnv the processing env
     */
    protected abstract void doInit(@Nonnull ProcessingEnvironment processingEnv);

    protected void debug(String message) {
        javac.debugMessage(message);
    }

    protected void notice(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }

    protected void warning(String message) {
        messager.printMessage(Diagnostic.Kind.WARNING, message);
    }

    protected void error(String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message);
    }
}