package org.example.io

import java.io.Reader
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class KtsObjectLoader(classLoader: ClassLoader? = Thread.currentThread().contextClassLoader) {

    val engine: ScriptEngine = ScriptEngineManager(classLoader).getEngineByExtension("kts")

    inline fun <reified T> load(script: String): T {
        return kotlin.runCatching { engine.eval(script) }
                .getOrElse { throw RuntimeException("Cannot load script", it) }
                .castOrError()
    }

    inline fun <reified T> load(reader: Reader): T {
        return kotlin.runCatching { engine.eval(reader) }
                .getOrElse { throw RuntimeException("Cannot load script", it) }
                .castOrError()
    }

    inline fun <reified T> Any?.castOrError(): T {
        return takeIf { it is T }?.let { it as T }
                ?: throw IllegalArgumentException("Cannot cast $this to expected type ${T::class}")
    }
}
