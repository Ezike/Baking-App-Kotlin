import Dependencies.AndroidX
import Dependencies.DI
import Dependencies.Google
import Dependencies.Kotlin
import Dependencies.View
import ProjectLib.recipe

plugins {
    androidApplication
    kotlin(kotlinAndroid)
    kotlin(kotlinAndroidExtension)
    kotlin(kotlinKapt)
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

    implementAll(View.components)
    implementation(Kotlin.stdlib)
    implementation(Google.playCore)

    implementation(DI.daggerHiltAndroid)

    AndroidX.run {
        implementation(activity)
        implementation(coreKtx)
        implementation(viewModel)
        implementation(navigationFragmentKtx)
        implementation(navigationUiKtx)
        implementation(navigationDFM)
        implementation(multiDex)
        implementation(lifeCycleCommon)
    }

    kapt(DI.AnnotationProcessor.daggerHiltAndroid)
}
