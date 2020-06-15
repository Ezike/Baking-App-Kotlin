import Dependencies.AndroidX
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.View
import ProjectLib.app
import ProjectLib.core
import ProjectLib.domain
import ProjectLib.presentation
import ProjectLib.recipeModel

plugins {
    dynamicFeature
    kotlin(kotlinAndroid)
    kotlin(kotlinKapt)
    daggerHilt
}

android {
    compileSdkVersion(Config.Version.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Config.Version.minSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }
}

dependencies {
    implementation(project(app))
    implementation(project(core))
    implementation(project(presentation))
    implementation(project(recipeModel))
    implementation(project(domain))

    implementAll(View.components)
    implementation(View.recyclerView)
    implementation(View.shimmerLayout)

    implementation(DI.daggerHiltAndroid)

    implementation(Kotlin.stdlib)
    implementAll(AndroidX.components)
    implementAll(Coroutines.components)

    kapt(DI.AnnotationProcessor.daggerHiltAndroid)
}
