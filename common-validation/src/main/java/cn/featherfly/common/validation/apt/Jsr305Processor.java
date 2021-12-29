package cn.featherfly.common.validation.apt;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.util.List;

import cn.featherfly.common.ast.JavacProcessor;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "javax.annotation.Nonnull" })
@SupportedOptions("debug")
public class Jsr305Processor extends JavacProcessor {

    @Override
    public void doInit(@Nonnull ProcessingEnvironment processingEnv) {
        //        messager.printMessage(Diagnostic.Kind.NOTE, "SupportedOptions");
        //        messager.printMessage(Diagnostic.Kind.NOTE, getSupportedOptions().toString());
        //        messager.printMessage(Diagnostic.Kind.NOTE, "getSupportedAnnotationTypes");
        //        messager.printMessage(Diagnostic.Kind.NOTE, getSupportedAnnotationTypes().toString());
        //        messager.printMessage(Diagnostic.Kind.NOTE, "processingEnv.getOptions()");
        //        messager.printMessage(Diagnostic.Kind.NOTE, processingEnv.getOptions().toString());
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final JavacElements elementUtils = (JavacElements) processingEnv.getElementUtils();
        debug("start generate check for javax.annotation.Nonnull");

        @SuppressWarnings("unchecked")
        Set<Element> elements = (Set<Element>) roundEnvironment.getElementsAnnotatedWith(Nonnull.class);

        debug("Nonnull annotated element size = " + elements.size());

        for (Element element : elements) {
            if (element.getKind() == ElementKind.PARAMETER) {
                JCMethodDecl jcMethodDecl = (JCMethodDecl) elementUtils.getTree(element.getEnclosingElement());
                JCClassDecl jcClassDecl = (JCClassDecl) elementUtils
                        .getTree(element.getEnclosingElement().getEnclosingElement());
                JCVariableDecl jcVariableDecl = (JCVariableDecl) elementUtils.getTree(element);
                //                addImportInfo(element, IllegalArgumentException.class);
                addAssertToBody(jcClassDecl, jcMethodDecl, jcVariableDecl);
                //                throw new IllegalArgumentException(
                //                        String.format("parameter [%s] can not be null", element.getSimpleName().toString()));
                notice(javac.getImportInfos(element).toString());
                notice("Nonnull -> " + element.getEnclosingElement().getKind() + " "
                        + element.getEnclosingElement().getSimpleName() + " " + element.getKind() + " "
                        + element.getSimpleName());
            }
        }

        debug("end generate check for javax.annotation.Nonnull");
        return true;
    }

    private void addAssertToBody(JCClassDecl jcClassDecl, JCMethodDecl jcMethodDecl, JCVariableDecl jcVariableDecl) {
        //        notice( "addAssertToBody ");
        //        notice( jcMethodDecl.name.toString());
        //        notice( jcVariableDecl.name.toString());
        //        notice( jcMethodDecl.body.toString());
        int paramIndex = jcMethodDecl.params.indexOf(jcVariableDecl);
        String message = String.format("class %s method %s args[%d] - %s  can not be null", jcClassDecl.name.toString(),
                jcMethodDecl.name.toString(), paramIndex, jcVariableDecl.name.toString());
        JCTree.JCExpression isEq = javac.eqNull(jcVariableDecl);
        JCStatement throwException = javac.throwException(IllegalArgumentException.class, message);
        JCIf jcIf = treeMaker.If(isEq, treeMaker.Block(0, List.of(throwException)), null);
        //        javac.addImportInfo(null, getClass());
        notice(jcVariableDecl.vartype + " : " + jcVariableDecl.name + " : " + jcVariableDecl.pos + ": "
                + jcVariableDecl.type + " : " + jcVariableDecl.nameexpr);
        List<JCTree.JCStatement> stats = List.of(jcIf);
        stats = stats.appendList(jcMethodDecl.body.getStatements());
        jcMethodDecl.body.stats = List.from(stats);
        //        notice( " ********************** ");
        //        notice( " ############### ");
        //        notice( jcMethodDecl.body.toString());
        //        jcMethodDecl.body = treeMaker
        //                .Block(0, List.of(
        //                        treeMaker.Exec(treeMaker.Apply(List.<JCTree.JCExpression>nil(),
        //                                treeMaker.Select(treeMaker.Select(treeMaker.Ident(elementUtils.getName("System")),
        //                                        elementUtils.getName("out")), elementUtils.getName("println")),
        //                                List.<JCTree.JCExpression>of(treeMaker.Literal("Hello, world!!!")))),
        //                        jcMethodDecl.body));

    }

}