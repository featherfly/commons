package cn.featherfly.common.validation.apt;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

import cn.featherfly.common.ast.Javac;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "javax.annotation.Nonnull" })
@SupportedOptions("debug")
public class Jsr305Processor extends AbstractProcessor {

    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;
    private JavacElements elementUtils;

    private Javac javac;

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

        messager.printMessage(Diagnostic.Kind.NOTE, "SupportedOptions");
        messager.printMessage(Diagnostic.Kind.NOTE, getSupportedOptions().toString());
        messager.printMessage(Diagnostic.Kind.NOTE, "getSupportedAnnotationTypes");
        messager.printMessage(Diagnostic.Kind.NOTE, getSupportedAnnotationTypes().toString());
        messager.printMessage(Diagnostic.Kind.NOTE, "processingEnv.getOptions()");
        messager.printMessage(Diagnostic.Kind.NOTE, processingEnv.getOptions().toString());
    }

    private void addAssertToBody(JCMethodDecl jcMethodDecl, JCVariableDecl jcVariableDecl) {
        messager.printMessage(Diagnostic.Kind.NOTE, "addAssertToBody ");
        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.name.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, jcVariableDecl.name.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.body.toString());

        String message = String.format("method %s args[%s] can not be null", jcMethodDecl.name.toString(),
                jcVariableDecl.name.toString());

        //        JCTree.JCExpression isEq = treeMaker.Binary(JCTree.Tag.EQ, treeMaker.Ident(jcVariableDecl),
        //                treeMaker.Literal(TypeTag.BOT, null));
        JCTree.JCExpression isEq = javac.eqNull(jcVariableDecl);

        //        JCStatement throwException = treeMaker
        //                .Throw(treeMaker.NewClass(null, List.nil(),
        //                        treeMaker.Ident(names.fromString(IllegalArgumentException.class.getSimpleName())),
        //                        List.of(treeMaker.Literal(message)), null));
        JCStatement throwException = javac.throwException(IllegalArgumentException.class, message);

        JCIf jcIf = treeMaker.If(isEq, treeMaker.Block(0, List.of(throwException)), null);

        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.body.getStatements().toString());

        List<JCTree.JCStatement> stats = List.of(jcIf);
        stats = stats.appendList(jcMethodDecl.body.getStatements());
        jcMethodDecl.body.stats = List.from(stats);

        messager.printMessage(Diagnostic.Kind.NOTE, " ********************** ");
        messager.printMessage(Diagnostic.Kind.NOTE, " ############### ");
        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.body.toString());

        //        jcMethodDecl.body = treeMaker
        //                .Block(0, List.of(
        //                        treeMaker.Exec(treeMaker.Apply(List.<JCTree.JCExpression>nil(),
        //                                treeMaker.Select(treeMaker.Select(treeMaker.Ident(elementUtils.getName("System")),
        //                                        elementUtils.getName("out")), elementUtils.getName("println")),
        //                                List.<JCTree.JCExpression>of(treeMaker.Literal("Hello, world!!!")))),
        //                        jcMethodDecl.body));

    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final JavacElements elementUtils = (JavacElements) processingEnv.getElementUtils();
        messager.printMessage(Diagnostic.Kind.NOTE, "start generate check");
        messager.printMessage(Diagnostic.Kind.NOTE,
                "Nonull element size = " + roundEnvironment.getElementsAnnotatedWith(Nonnull.class).size());

        for (Element element : roundEnvironment.getElementsAnnotatedWith(Nonnull.class)) {
            if (element.getKind() == ElementKind.PARAMETER) {
                String methodName = element.getEnclosingElement().getSimpleName().toString();
                String paramName = element.getSimpleName().toString();
                JCMethodDecl jcMethodDecl = (JCMethodDecl) elementUtils.getTree(element.getEnclosingElement());
                JCVariableDecl jcVariableDecl = (JCVariableDecl) elementUtils.getTree(element);

                //                addImportInfo(element, IllegalArgumentException.class);

                addAssertToBody(jcMethodDecl, jcVariableDecl);
                //                throw new IllegalArgumentException(
                //                        String.format("parameter [%s] can not be null", element.getSimpleName().toString()));
            }

            if (element.getKind() == ElementKind.PARAMETER || element.getKind() == ElementKind.CONSTRUCTOR) {

            }

            String name = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "Nonnull ->" + element.getKind() + "-" + name);
        }

        messager.printMessage(Diagnostic.Kind.NOTE, "end generate check");
        return true;
    }
}