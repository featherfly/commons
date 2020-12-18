
package cn.featherfly.common.script.kotlin;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.jetbrains.kotlin.cli.common.environment.UtilKt;

/**
 * KotlinScriptEngine.
 *
 * @author zhongj
 */
public class KotlinScript {

    public static final ScriptEngine KOTLIN_ENGINE;

    static {
        UtilKt.setIdeaIoUseFallback();
        ScriptEngineManager factory = new ScriptEngineManager();
        KOTLIN_ENGINE = factory.getEngineByExtension("kts");
    }

    public static ScriptEngine getEngine() {
        return KOTLIN_ENGINE;
    }
}
