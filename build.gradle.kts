buildscript {
    repositories.applyDefault()
    dependencies {
        addPlugins(Config.Plugin.components)
    }
}

allprojects {
    repositories.applyDefault()
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}