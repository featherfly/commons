
package cn.featherfly.common.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.tools.Diagnostic;

import com.sun.source.tree.Tree;
import com.sun.source.util.TreePath;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCBinary;
import com.sun.tools.javac.tree.JCTree.JCImport;
import com.sun.tools.javac.tree.JCTree.JCThrow;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

/**
 * <p>
 * JavacUtils
 * </p>
 * .
 *
 * @author zhongj
 */
public class Javac {

    private TreeMaker maker;

    private JavacTrees trees;

    private Names names;

    private Messager messager;

    private ProcessingEnvironment processingEnv;

    /**
     * Instantiates a new javac.
     *
     * @param maker         the maker
     * @param trees         the trees
     * @param names         the names
     * @param messager      the messager
     * @param processingEnv the processing env
     */
    public Javac(TreeMaker maker, JavacTrees trees, Names names, Messager messager,
            ProcessingEnvironment processingEnv) {
        super();
        this.maker = maker;
        this.trees = trees;
        this.names = names;
        this.messager = messager;
        this.processingEnv = processingEnv;
    }

    /**
     * Throw exception.
     *
     * @param <E>              the element type
     * @param exceptionType    the exception type
     * @param exceptionMessage the exception message
     * @return the JC throw
     */
    public <E extends RuntimeException> JCThrow throwException(Class<E> exceptionType, String exceptionMessage) {
        return maker
                .Throw(maker.NewClass(null, List.nil(), maker.Ident(names.fromString(exceptionType.getSimpleName())),
                        List.of(maker.Literal(exceptionMessage)), null));
    }

    /**
     * Eq null.
     *
     * @param <E>            the element type
     * @param jcVariableDecl the jc variable decl
     * @return the JC binary
     */
    public <E extends RuntimeException> JCBinary eqNull(JCVariableDecl jcVariableDecl) {
        return maker.Binary(JCTree.Tag.EQ, maker.Ident(jcVariableDecl), maker.Literal(TypeTag.BOT, null));
    }

    public Collection<JCImport> getImportInfos(Element element) {
        Collection<JCImport> jcImports = new HashSet<>();
        if (element == null) {
            return jcImports;
        }
        if (element.getKind() != ElementKind.CLASS) {
            return getImportInfos(element.getEnclosingElement());
        }

        TreePath treePath = trees.getPath(element);
        Tree leaf = treePath.getLeaf();
        if (treePath.getCompilationUnit() instanceof JCTree.JCCompilationUnit && leaf instanceof JCTree) {
            JCTree.JCCompilationUnit jccu = (JCTree.JCCompilationUnit) treePath.getCompilationUnit();
            for (JCTree jcTree : jccu.getImports()) {
                if (jcTree != null && jcTree instanceof JCTree.JCImport) {
                    jcImports.add((JCTree.JCImport) jcTree);
                }
            }
        }
        return jcImports;
    }

    /**
     * Adds the import info.
     *
     * @param element    the element
     * @param importType the import type
     */
    public void addImportInfo(Element element, Class<?> importType) {
        debugMessage("addImportInfo for type " + importType.toString());
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
                            messager.printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                        }
                    }
                }
            }
            java.util.List<JCTree> trees = new ArrayList<>();
            trees.addAll(jccu.defs);
            messager.printMessage(Diagnostic.Kind.NOTE, "jccu.defs");
            messager.printMessage(Diagnostic.Kind.NOTE, jccu.defs.toString());
            JCTree.JCIdent ident = maker.Ident(names.fromString(importType.getPackage().getName()));
            JCTree.JCImport jcImport = maker.Import(maker.Select(ident, names.fromString(importType.getSimpleName())),
                    false);
            if (!trees.contains(jcImport)) {
                debugMessage("add import " + jcImport.toString());
                trees.add(0, jcImport);
            }
            jccu.defs = List.from(trees);
        }
    }

    /**
     * Gets the ter method.
     *
     * @param jcVariableDecl the jc variable decl
     * @return the ter method
     */
    public JCTree.JCMethodDecl getterMethod(JCTree.JCVariableDecl jcVariableDecl) {
        JCTree.JCModifiers jcModifiers = maker.Modifiers(Flags.PUBLIC);//public
        JCTree.JCExpression retrunType = jcVariableDecl.vartype;//方法返回类型
        Name name = getGetterName(jcVariableDecl.name);// 方法名getXxx
        JCTree.JCStatement jcStatement = // retrun this.xxx
                maker.Return(maker.Select(maker.Ident(names.fromString("this")), jcVariableDecl.name));
        JCTree.JCBlock jcBlock = maker.Block(0, List.of(jcStatement)); //构建代码块
        List<JCTree.JCTypeParameter> methodGenericParams = List.nil();//泛型参数列表
        List<JCTree.JCVariableDecl> parameters = List.nil();//参数列表
        List<JCTree.JCExpression> throwsClauses = List.nil();//异常抛出列表
        JCTree.JCExpression defaultValue = null;//非自定义注解类中的方法，defaultValue为null
        //最后构建getter方法
        JCTree.JCMethodDecl jcMethodDecl = maker.MethodDef(jcModifiers, name, retrunType, methodGenericParams,
                parameters, throwsClauses, jcBlock, defaultValue);
        return jcMethodDecl;
    }

    /**
     * Make setter method.
     *
     * @param jcVariableDecl the jc variable decl
     * @return the JC tree. JC method decl
     */
    public JCTree.JCMethodDecl setterMethod(JCTree.JCVariableDecl jcVariableDecl) {
        JCTree.JCModifiers jcModifiers = maker.Modifiers(Flags.PUBLIC);//public
        JCTree.JCExpression retrunType = maker.TypeIdent(TypeTag.VOID);//或 treeMaker.Type(new Type.JCVoidType())
        Name name = getSetterName(jcVariableDecl.name);// setXxx()
        List<JCTree.JCVariableDecl> parameters = List.nil();//参数列表
        JCTree.JCVariableDecl param = maker.VarDef(maker.Modifiers(Flags.PARAMETER), jcVariableDecl.name,
                jcVariableDecl.vartype, null);
        param.pos = jcVariableDecl.pos;//设置形参这一句不能少，不然会编译报错(java.lang.AssertionError: Value of x -1)
        parameters = parameters.append(param);
        //this.xxx = xxx;  setter方法中的赋值语句
        JCTree.JCStatement jcStatement = maker
                .Exec(maker.Assign(maker.Select(maker.Ident(names.fromString("this")), jcVariableDecl.name),
                        maker.Ident(jcVariableDecl.name)));
        JCTree.JCBlock jcBlock = maker.Block(0, List.of(jcStatement));//代码块
        List<JCTree.JCTypeParameter> methodGenericParams = List.nil();//泛型参数列表
        List<JCTree.JCExpression> throwsClauses = List.nil();//异常抛出列表
        JCTree.JCExpression defaultValue = null;
        //最后构建setter方法
        JCTree.JCMethodDecl jcMethodDecl = maker.MethodDef(jcModifiers, name, retrunType, methodGenericParams,
                parameters, throwsClauses, jcBlock, defaultValue);
        return jcMethodDecl;
    }

    /**
     * Log.
     *
     * @param msg the msg
     * @return the javac
     */
    public Javac debugMessage(String msg) {
        if (processingEnv.getOptions().containsKey("debug")) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
        }
        return this;
    }

    private Name getGetterName(Name name) {
        String s = name.toString();
        return names.fromString("get" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }

    private Name getSetterName(Name name) {
        String s = name.toString();
        return names.fromString("set" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }
}
