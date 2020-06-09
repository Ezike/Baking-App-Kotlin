import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.initialization.dsl.ScriptHandler
import org.gradle.kotlin.dsl.apply
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

val PluginDependenciesSpec.androidApplication: PluginDependencySpec
    get() = { id("com.android.application") }()

val PluginDependenciesSpec.androidLibrary: PluginDependencySpec
    get() = { id("com.android.library") }()

val PluginDependenciesSpec.kotlin: PluginDependencySpec
    get() = { id("kotlin") }()

val PluginDependenciesSpec.daggerHilt: PluginDependencySpec
    get() = { id("dagger.hilt.android.plugin") }()

val Project.applySpotless: Unit
    get() = { apply(plugin = "spotless") }()

fun RepositoryHandler.maven(url: String) {
    maven {
        setUrl(url)
    }
}

fun RepositoryHandler.applyDefault() {
    google()
    jcenter()
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://androidx.dev/snapshots/builds/6518514/artifacts/repository/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

fun DependencyHandler.implementAll(list: List<String>) {
    list.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addPlugins(list: List<String>) {
    list.forEach {
        add(ScriptHandler.CLASSPATH_CONFIGURATION, it)
    }
}

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
        add("kapt", dependencyNotation)
