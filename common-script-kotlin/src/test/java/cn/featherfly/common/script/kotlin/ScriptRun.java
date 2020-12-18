
package cn.featherfly.common.script.kotlin;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

/**
 * ScriptRun.
 *
 * @author zhongj
 */
public class ScriptRun {

    static class Timer {
        long start;

        long end;

        void start() {
            start = System.currentTimeMillis();
        }

        void end() {
            end = System.currentTimeMillis();
        }

        long times() {
            return end - start;
        }
    }

    public static void eval(String script, int times, ScriptEngine engine, Bindings bindings) throws ScriptException {
        Timer timer = new Timer();
        for (int i = 0; i < times; i++) {
            timer.start();
            engine.eval(script, bindings);
            timer.end();
            System.out.println("index " + i + " use time: " + timer.times());
        }
    }

    @org.testng.annotations.Test
    public void test() throws ScriptException {
        ScriptEngine engine = KotlinScript.getEngine();
        SimpleBindings bindings = new SimpleBindings();
        engine.put("name", "kotlin script");
        bindings.put("name", "kotlin script");
        //        engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
        String script = "var result = \"hello \";println(result)";
        //        String script = "println(\"hello $name\")";
        eval(script, 5, engine, bindings);
    }
}
