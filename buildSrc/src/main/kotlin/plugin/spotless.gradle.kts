plugins {
    id("com.diffplug.gradle.spotless")
}

spotless {
    kotlin {
        target(fileTree(mapOf(
                "dir" to ".",
                "include" to listOf("**/*.kt"),
                "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*", ".idea/"))))
        ktlint(ktLintVersion)
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    format("xml") {
        target("**/res/**/*.xml")
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
    kotlinGradle {
        target(fileTree(mapOf(
                "dir" to ".",
                "include" to listOf("**/*.gradle.kts", "*.gradle.kts"),
                "exclude" to listOf("**/build/**"))))
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
}
