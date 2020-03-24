package org.example

import org.example.dsl.HTML
import org.example.io.KtsObjectLoader
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    val loader = KtsObjectLoader()
    val script = Files.newBufferedReader(Paths.get("html.config.kts"))
    val html = loader.load<HTML>(script)
    println(html)
}