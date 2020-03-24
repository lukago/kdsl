package org.example

import org.example.dsl.HTML
import org.example.io.KtsObjectLoader
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths

class ConfigTest {

    @Test
    fun `config should be loaded`() {
        val loader = KtsObjectLoader()
        val script = Files.newBufferedReader(Paths.get("html.config.kts"))
        val html = loader.load<HTML>(script.readText())

        assertTrue(html.children.size > 0)
    }
}