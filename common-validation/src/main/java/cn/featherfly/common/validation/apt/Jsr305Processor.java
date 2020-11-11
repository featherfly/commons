package cn.featherfly.common.validation.apt;

import java.util.ArrayList;
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

import com.sun.source.tree.Tree;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCIf;
import com.sun.tools.javac.tree.JCTree.JCLiteral;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCStatement;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "javax.annotation.Nonnull" })
@SupportedOptions("debug")
public class Jsr305Processor extends AbstractProcessor {

    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;
    private JavacElements elementUtils;

    @Override
    public synchronized void init(@Nonnull ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        treeMaker = TreeMaker.instance(context);
        names = Names.instance(context);
        elementUtils = (JavacElements) processingEnv.getElementUtils();

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

        //        messager.printMessage(Diagnostic.Kind.NOTE, "************************");
        //        messager.printMessage(Diagnostic.Kind.NOTE,
        //                treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())).toString());
        //        messager.printMessage(Diagnostic.Kind.NOTE, treeMaker
        //                .TypeApply(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())), List.nil())
        //                .toString());
        //        messager.printMessage(Diagnostic.Kind.NOTE,
        //                treeMaker.NewClass(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())),
        //                        List.of(treeMaker.Literal("2345")), null, List.nil(), null).toString());
        //        messager.printMessage(Diagnostic.Kind.NOTE,
        //                treeMaker.NewClass(null, List.nil(),
        //                        treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())),
        //                        List.of(treeMaker.Literal("2345")), null).toString());
        //        messager.printMessage(Diagnostic.Kind.NOTE,
        //                treeMaker.NewClass(
        //                        treeMaker.TypeApply(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())),
        //                                List.nil()),
        //                        List.of(treeMaker.Literal("2345")), null, List.nil(), null).toString());

        JCTree.JCExpression isEq = treeMaker.Binary(JCTree.Tag.EQ, treeMaker.Ident(jcVariableDecl),
                treeMaker.Literal(TypeTag.BOT, null));
        JCExpression e = null;
        JCLiteral c = null;

        JCStatement ifNull = treeMaker
                //                .Throw(treeMaker.NewClass(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())), null, null, null, null));
                //                .Throw(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())));
                .Throw(treeMaker.NewClass(null, List.nil(),
                        treeMaker.Ident(names.fromString(IllegalArgumentException.class.getSimpleName())),
                        List.of(treeMaker.Literal(message)), null));
        //        treeMaker.Block(0, List.of(ifNull, treeMaker.Literal("("), treeMaker.Literal("(")));

        //        treeMaker.NewClass(
        //                treeMaker.TypeApply(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())), null),
        //                List.of(treeMaker.Literal("2345")), null, null, null);

        //        new JCTypeApply();

        //        treeMaker.TypeApply(treeMaker.Ident(names.fromString(IllegalArgumentException.class.getName())),
        //                List.of(treeMaker.Literal("123456")));

        //        throw new IllegalArgumentException();

        //        JCExpression

        //        treeMaker.NEW

        //        JCIf jcIf = treeMaker.If(isEq, List.of(treeMaker.Exec(ifNull)), null);
        JCIf jcIf = treeMaker.If(isEq, treeMaker.Block(0, List.of(ifNull)), null);
        //        treeMaker.Block(0, List.of(arg0))
        //        List.<JCTree.JCExpression>of(treeMaker.Literal("Hello, world!!!"));

        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.body.getStatements().toString());

        //        java.util.List<JCStatement> stats = new ArrayList<>(jcMethodDecl.body.getStatements().size() + 1);
        //        stats.add(jcIf);
        //        stats.addAll(jcMethodDecl.body.getStatements());

        List<JCTree.JCStatement> stats = List.of(jcIf);
        stats = stats.appendList(jcMethodDecl.body.getStatements());
        jcMethodDecl.body.stats = List.from(stats);
        //        jcMethodDecl.body = treeMaker.Block(0, List.from(stats));
        //        jcMethodDecl.body = treeMaker.Block(0, List.of(jcIf, jcMethodDecl.body));

        //        jcMethodDecl.body = treeMaker.Block(0, List.of(treeMaker.Block(0, List.of(jcIf)), jcMethodDecl.body));

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

        //        for (Element element : roundEnvironment.getRootElements()) {
        //            String name = element.getSimpleName().toString();
        //            messager.printMessage(Diagnostic.Kind.NOTE, element.getKind() + "-" + name);
        //            JCClassDecl jcClassDecl = (JCClassDecl) elementUtils.getTree(element);
        //
        //            for (JCTree jcTreeMember : jcClassDecl.getMembers()) {
        //                messager.printMessage(Diagnostic.Kind.NOTE, jcTreeMember.getKind() + " <> " + jcTreeMember.toString());
        //            }
        //            messager.printMessage(Diagnostic.Kind.NOTE, "***********************");
        //            JCTree jcTree = trees.getTree(element);
        //
        //            jcTree.accept(new TreeTranslator() {
        //
        //                @Override
        //                public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
        //                    List<JCTree.JCMethodDecl> jcMethodDeclList = List.nil();
        //                    for (JCTree tree : jcClassDecl.defs) {
        //                        messager.printMessage(Diagnostic.Kind.NOTE, tree.getKind() + " <> " + tree.toString());
        //                        if (tree.getKind() == Tree.Kind.IMPORT) {
        //                            messager.printMessage(Diagnostic.Kind.NOTE, "import: " + tree.toString());
        //                        }
        //
        //                        if (tree.getKind() == Tree.Kind.METHOD) {
        //                            JCTree.JCMethodDecl jcMethodDecl = (JCTree.JCMethodDecl) tree;
        //                            messager.printMessage(Diagnostic.Kind.NOTE, "name: " + jcMethodDecl.name);
        //                            //                            messager.printMessage(Diagnostic.Kind.NOTE, "parameters: " + jcMethodDecl.getParameters());
        //                            //                            messager.printMessage(Diagnostic.Kind.NOTE,
        //                            //                                    "typeParameters: " + jcMethodDecl.getTypeParameters());
        //                            for (JCVariableDecl jcVariableDecl : jcMethodDecl.getParameters()) {
        //                                messager.printMessage(Diagnostic.Kind.NOTE,
        //                                        "\tparam: " + jcVariableDecl.name.toString());
        //                                for (AnnotationTree annotationTree : jcVariableDecl.mods.getAnnotations()) {
        //                                    messager.printMessage(Diagnostic.Kind.NOTE,
        //                                            "\t\tkind: " + annotationTree.getKind());
        //                                    messager.printMessage(Diagnostic.Kind.NOTE,
        //                                            "\t\tannotation: " + annotationTree.getAnnotationType());
        //                                    messager.printMessage(Diagnostic.Kind.NOTE,
        //                                            "\t\tannotation: " + annotationTree.getAnnotationType());
        //                                }
        //                                jcMethodDecl.body = treeMaker
        //                                        .Block(0, List.of(
        //                                                treeMaker
        //                                                        .Exec(treeMaker
        //                                                                .Apply(List.<JCTree.JCExpression>nil(),
        //                                                                        treeMaker.Select(
        //                                                                                treeMaker.Select(
        //                                                                                        treeMaker.Ident(elementUtils
        //                                                                                                .getName("System")),
        //                                                                                        elementUtils.getName("out")),
        //                                                                                elementUtils.getName("println")),
        //                                                                        List.<JCTree.JCExpression>of(
        //                                                                                treeMaker.Literal("Hello, world!!!")))),
        //                                                jcMethodDecl.body));
        //                            }
        //                        }
        //                    }
        //                    messager.printMessage(Diagnostic.Kind.NOTE, "****************************");
        //                    messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDeclList.size() + "");
        //                    jcMethodDeclList.forEach(jcMethodDecl -> {
        //                        messager.printMessage(Diagnostic.Kind.NOTE, jcMethodDecl.name + " has been processed");
        //                        JCMethodDecl setter = makeGetterMethodDecl(jcMethodDecl);
        //                        messager.printMessage(Diagnostic.Kind.NOTE, " setter name: " + setter.name);
        //                        jcClassDecl.defs = jcClassDecl.defs.prepend(setter);
        //                        jcClassDecl.defs = jcClassDecl.defs.prepend(makeSetterMethodDecl(jcMethodDecl));
        //                    });
        //                    super.visitClassDef(jcClassDecl);
        //                }
        //            });
        //        }
        messager.printMessage(Diagnostic.Kind.NOTE, "end generate check");
        return true;
    }

    private void addImportInfo(Element element, Class<?> importType) {
        messager.printMessage(Diagnostic.Kind.NOTE, "addImportInfo for type " + importType.toString());
        TreePath treePath = trees.getPath(element);
        Tree leaf = treePath.getLeaf();
        if (treePath.getCompilationUnit() instanceof JCTree.JCCompilationUnit && leaf instanceof JCTree) {
            JCTree.JCCompilationUnit jccu = (JCTree.JCCompilationUnit) treePath.getCompilationUnit();

            for (JCTree jcTree : jccu.getImports()) {
                if (jcTree != null && jcTree instanceof JCTree.JCImport) {
                    JCTree.JCImport jcImport = (JCTree.JCImport) jcTree;
                    if (jcImport.qualid != null && jcImport.qualid instanceof JCTree.JCFieldAccess) {
                        JCTree.JCFieldAccess jcFieldAccess = (JCTree.JCFieldAccess) jcImport.qualid;
                        try {
                            if (importType.getPackage().getName().equals(jcFieldAccess.selected.toString())
                                    && importType.getSimpleName().equals(jcFieldAccess.name.toString())) {
                                return;
                            }
                        } catch (NullPointerException e) {
                            messager.printMessage(Diagnostic.Kind.NOTE, e.getMessage());
                        }
                    }
                }
            }
            java.util.List<JCTree> trees = new ArrayList<>();
            trees.addAll(jccu.defs);
            messager.printMessage(Diagnostic.Kind.NOTE, "jccu.defs");
            messager.printMessage(Diagnostic.Kind.NOTE, jccu.defs.toString());
            JCTree.JCIdent ident = treeMaker.Ident(names.fromString(importType.getPackage().getName()));
            JCTree.JCImport jcImport = treeMaker
                    .Import(treeMaker.Select(ident, names.fromString(importType.getSimpleName())), false);
            if (!trees.contains(jcImport)) {
                messager.printMessage(Diagnostic.Kind.NOTE, "add import " + jcImport.toString());
                trees.add(0, jcImport);
            }
            jccu.defs = List.from(trees);
            messager.printMessage(Diagnostic.Kind.NOTE, "jccu.defs");
            messager.printMessage(Diagnostic.Kind.NOTE, jccu.defs.toString());
        }
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

    //    /**
    //     * Generates a new statement that checks if the given local is null, and if
    //     * so, throws a configured exception with the local variable name as
    //     * message.
    //     */
    //    public static JCStatement generateNullCheck(JavacTreeMaker maker, JCExpression typeNode, Name varName,
    //            JavacNode source, String customMessage) {
    //        NullCheckExceptionType exceptionType = source.getAst()
    //                .readConfiguration(ConfigurationKeys.NON_NULL_EXCEPTION_TYPE);
    //        if (exceptionType == null) {
    //            exceptionType = NullCheckExceptionType.NULL_POINTER_EXCEPTION;
    //        }
    //
    //        if (typeNode != null && isPrimitive(typeNode)) {
    //            return null;
    //        }
    //        JCLiteral message = maker.Literal(exceptionType.toExceptionMessage(varName.toString(), customMessage));
    //
    //        LombokImmutableList<String> method = exceptionType.getMethod();
    //        if (method != null) {
    //            return maker.Exec(maker.Apply(List.<JCExpression>nil(), chainDots(source, method),
    //                    List.of(maker.Ident(varName), message)));
    //        }
    //
    //        if (exceptionType == NullCheckExceptionType.ASSERTION) {
    //            return maker.Assert(maker.Binary(CTC_NOT_EQUAL, maker.Ident(varName), maker.Literal(CTC_BOT, null)),
    //                    message);
    //        }
    //
    //        JCExpression exType = genTypeRef(source, exceptionType.getExceptionType());
    //        JCExpression exception = maker.NewClass(null, List.<JCExpression>nil(), exType, List.<JCExpression>of(message),
    //                null);
    //        JCStatement throwStatement = maker.Throw(exception);
    //        JCBlock throwBlock = maker.Block(0, List.of(throwStatement));
    //        return maker.If(maker.Binary(CTC_EQUAL, maker.Ident(varName), maker.Literal(CTC_BOT, null)), throwBlock, null);
    //    }

}