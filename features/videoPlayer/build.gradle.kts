import Dependencies.AndroidX
import Dependencies.Kotlin
import Dependencies.View.appCompat
import Dependencies.View.constraintLayout
import Dependencies.View.exoPlayerCore
import Dependencies.View.exoPlayerUI
import ProjectLib.app

plugins {
    dynamicFeature
    kotlin(kotlinAndroid)
    kotlin(kotlinAndroidExtension)
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

    implementation(appCompat)
    implementation(exoPlayerCore)
    implementation(exoPlayerUI)
    implementation(constraintLayout)
    implementation(AndroidX.lifeCycleCommon)

    implementation(Kotlin.stdlib)
}
