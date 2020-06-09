import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    jcenter()
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = "1.4-M2"
}

object Plugin {
    object Version {
        const val spotless: String = "4.0.1"
    }

    const val spotless: String = "com.diffplug.spotless:spotless-plugin-gradle:${Version.spotless}"
}

dependencies {
    implementation(Plugin.spotless)
}
