package cn.featherfly.common.validation.apt;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "javax.validation.constraints.AssertFalse", "javax.validation.constraints.AssertTrue",
        "javax.validation.constraints.Digits", "javax.validation.constraints.DecimalMax",
        "javax.validation.constraints.DecimalMin", "javax.validation.constraints.Email",
        "javax.validation.constraints.Future", "javax.validation.constraints.FutureOrPresent",
        "javax.validation.constraints.Max", "javax.validation.constraints.Min", "javax.validation.constraints.Negative",
        "javax.validation.constraints.NegativeOrZero", "javax.validation.constraints.NotBlank",
        "javax.validation.constraints.NotEmpty", "javax.validation.constraints.NotNull",
        "javax.validation.constraints.Null", "javax.validation.constraints.Past",
        "javax.validation.constraints.PastOrPresent", "javax.validation.constraints.Pattern",
        "javax.validation.constraints.Positive", "javax.validation.constraints.PositiveOrZero",
        "javax.validation.constraints.Size" })
@SupportedOptions("debug")
public class ValidationProcessor extends AbstractProcessor {

    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        final JavacElements elementUtils = (JavacElements) processingEnv.getElementUtils();
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "start generate validation");
        messager.printMessage(Diagnostic.Kind.NOTE, "" + roundEnvironment.getRootElements().size());

        //        for (Element element : roundEnvironment.getElementsAnnotatedWith(NotNull.class)) {
        //            String name = element.getSimpleName().toString();
        //            messager.printMessage(Diagnostic.Kind.NOTE, element.getKind() + " - " + name);
        //            messager.printMessage(Diagnostic.Kind.NOTE,
        //                    element.getEnclosingElement().getKind() + " - " + element.getEnclosingElement().getSimpleName());
        //        }

        //        JCTree.JCMethodDecl jcMethodDecl = (JCTree.JCMethodDecl) elementUtils.getTree(element);
        //        treeMaker.pos = jcMethodDecl.pos;
        //        jcMethodDecl.body = treeMaker
        //                .Block(0, List.of(
        //                        treeMaker.Exec(treeMaker.Apply(List.<JCTree.JCExpression>nil(),
        //                                treeMaker.Select(treeMaker.Select(treeMaker.Ident(elementUtils.getName("System")),
        //                                        elementUtils.getName("out")), elementUtils.getName("println")),
        //                                List.<JCTree.JCExpression>of(treeMaker.Literal("Hello, world!!!")))),
        //                        jcMethodDecl.body));

        for (Element element : roundEnvironment.getRootElements()) {
            String name = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, element.getKind() + "-" + name);
            JCTree jcTree = trees.getTree(element);
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                    List<JCTree.JCMethodDecl> jcMethodDeclList = List.nil();
                    for (JCTree tree : jcClassDecl.defs) {
                        if (tree.getKind() == Tree.Kind.METHOD) {
                            JCTree.JCMethodDecl jcMethodDecl = (JCTree.JCMethodDecl) tree;
                            messager.printMessage(Diagnostic.Kind.NOTE, "name: " + jcMethodDecl.name);
                            //                            messager.printMessage(Diagnostic.Kind.NOTE, "parameters: " + jcMethodDecl.getParameters());
                            //                            messager.printMessage(Diagnostic.Kind.NOTE,
                            //                                    "typeParameters: " + jcMethodDecl.getTypeParameters());
                            for (JCVariableDecl jcVariableDecl : jcMethodDecl.getParameters()) {
                                messager.printMessage(Diagnostic.Kind.NOTE,
                                        "\tparam: " + jcVariableDecl.name.toString());
                                for (AnnotationTree annotationTree : jcVariableDecl.mods.getAnnotations()) {
                                    messager.printMessage(Diagnostic.Kind.NOTE,
                                            "\t\tannotation: " + annotationTree.getAnnotationType());
                                    messager.printMessage(Diagnostic.Kind.NOTE,
                                            "\t\tannotation: " + annotationTree.getAnnotationType());
                                }
                            }
                            if (jcMethodDecl.name.toString().equals("notNull")) {
                                messager.printMessage(Diagnostic.Kind.NOTE,
                                        jcMethodDecl.name + "\n" + jcMethodDecl.body.toString());
                                messager.printMessage(Diagnostic.Kind.NOTE, "add System.out.println('Hello, world')");
                                treeMaker.pos = jcMethodDecl.pos;
                                jcMethodDecl.body = treeMaker
                                        .Block(0, List.of(
                                                treeMaker
                                                        .Exec(treeMaker
                                                                .Apply(List.<JCTree.JCExpression>nil(),
                                                                        treeMaker.Select(
                                                                                treeMaker.Select(
                                                                                        treeMaker.Ident(elementUtils
                                                                                                .getName("System")),
                                                                                        elementUtils.getName("out")),
                                                                                elementUtils.getName("println")),
                                                                        List.<JCTree.JCExpression>of(
                                                                                treeMaker.Literal("Hello, world!!!")))),
                                                jcMethodDecl.body));
                                messager.printMessage(Diagnostic.Kind.NOTE,
                                        jcMethodDecl.name + "\n" + jcMethodDecl.body.toString());
                            }
                        }
                    }
                    //                    messager.printMessage(Diagnostic.Kind.NOTE, "****************************");
                    //                    messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDeclList.size() + "");
                    //                    jcMethodDeclList.forEach(jcMethodDecl -> {
                    //                        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.name + " has been processed");
                    //                        JCMethodDecl setter = makeGetterMethodDecl(jcMethodDecl);
                    //                        messager.printMessage(Diagnostic.Kind.NOTE, " setter name: " + setter.name);
                    //                        jcClassDecl.defs = jcClassDecl.defs.prepend(setter);
                    //                        jcClassDecl.defs = jcClassDecl.defs.prepend(makeSetterMethodDecl(jcMethodDecl));
                    //                    });
                    super.visitClassDef(jcClassDecl);
                }
            });
        }
        messager.printMessage(Diagnostic.Kind.NOTE, "end generate validation");
        return true;
    }

    private JCTree.JCMethodDecl makeGetterMethodDecl(JCTree.JCMethodDecl jcMethodDecl) {
        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.InterfaceMethodFlags),
                getGetterName(jcMethodDecl.getName()), jcMethodDecl.restype, List.nil(), List.nil(), List.nil(), null,
                null);
    }

    /*
    public JCMethodDecl MethodDef(JCModifiers mods,
            Name name,
            JCExpression restype,
            List<JCTypeParameter> typarams,
            List<JCVariableDecl> params,
            List<JCExpression> thrown,
            JCBlock body,
            JCExpression defaultValue)
    */
    private JCTree.JCMethodDecl makeSetterMethodDecl(JCTree.JCMethodDecl jcMethodDecl) {
        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.InterfaceMethodFlags),
                getSetterName(jcMethodDecl.getName()), null, // return type
                jcMethodDecl.typarams, jcMethodDecl.params, List.nil(), null, null);
    }

    private Name getGetterName(Name name) {
        String s = name.toString();
        return names.fromString("get" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }

    private Name getSetterName(Name name) {
        String s = name.toString();
        return names.fromString("set" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }

    private void log(String msg) {
        if (processingEnv.getOptions().containsKey("debug")) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
        }
    }

}