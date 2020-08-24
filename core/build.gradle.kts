import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Network
import Dependencies.View
import ProjectLib.data
import ProjectLib.domain
import ProjectLib.remote

plugins {
    androidLibrary
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
    implementation(project(domain))
    implementation(project(data))
    implementation(project(remote))

    implementation(DI.daggerHiltAndroid)
    implementation(Network.moshi)
    implementation(Coroutines.core)
    implementation(View.coil)
    implementation(View.fragment)
    implementation(View.appCompat)

    kapt(DI.AnnotationProcessor.daggerHiltAndroid)
}
