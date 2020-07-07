import Dependencies.Test
import ProjectLib.app
import ProjectLib.domain
import ProjectLib.recipeModel

plugins {
    androidLibrary
    kotlin(kotlinAndroid)
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
}

dependencies {
    implementation(project(domain))
    implementation(project(app))
    implementation(project(recipeModel))
    api(Test.junit)
    api(Test.truth)
    api(Test.coroutinesTest)
}
