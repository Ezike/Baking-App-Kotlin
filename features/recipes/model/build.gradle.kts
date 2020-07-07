import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.Test
import ProjectLib.domain
import ProjectLib.presentation

plugins {
    androidLibrary
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
    implementation(project(domain))
    implementation(project(presentation))
    implementation(Kotlin.stdlib)
    implementation(DI.javaxInject)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
}
