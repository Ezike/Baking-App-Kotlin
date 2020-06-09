import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories.applyDefault()
    dependencies {
        addPlugins(Config.Plugin.components)
    }
}

allprojects {
    applySpotless
    repositories.applyDefault()
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}
