import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.Network
import Dependencies.View
import ProjectLib.core
import ProjectLib.data
import ProjectLib.domain
import ProjectLib.recipe
import ProjectLib.remote

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinAndroidExtension)
    kotlin(kotlinKapt)
    safeArgs
    daggerHilt
}

android {
    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdkVersion(Config.Version.minSdkVersion)
        compileSdkVersion(Config.Version.compileSdkVersion)
        targetSdkVersion(Config.Version.targetSdkVersion)
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        multiDexEnabled = Config.isMultiDexEnabled
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
    }

    androidExtensions {
        isExperimental = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    @Suppress("UnstableApiUsage")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
        }
    }

    dynamicFeatures = mutableSetOf(recipe)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(core))
    implementation(project(data))
    implementation(project(domain))
    implementation(project(remote))

    implementAll(View.components)
    implementation(Kotlin.stdlib)
    implementation(Network.moshi)
    implementation(DI.daggerHiltAndroid)

    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(navigationDFM)
        implementation(multiDex)
        implementation(lifeCycleCommon)
    }

    kapt(DI.AnnotationProcessor.daggerHiltAndroid)
}
