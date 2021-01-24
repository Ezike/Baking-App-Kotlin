import Dependencies.AndroidX
import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.FlowBinding
import Dependencies.View
import ProjectLib.app
import ProjectLib.core
import ProjectLib.domain
import ProjectLib.presentation
import ProjectLib.presentationAndroid
import ProjectLib.recipeModel
import ProjectLib.videoPlayer

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
    implementation(project(presentationAndroid))
    implementation(project(videoPlayer))
    implementation(project(recipeModel))
    implementation(project(domain))

    implementation(FlowBinding.android)

    implementAll(View.components)
    implementation(DI.daggerHiltAndroid)
    implementAll(AndroidX.components)
    implementAll(Coroutines.components)

    kapt(DI.AnnotationProcessor.daggerHilt)
}
