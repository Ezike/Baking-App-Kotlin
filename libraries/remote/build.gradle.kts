import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Kotlin
import Dependencies.Network
import ProjectLib.data

plugins {
    kotlin
    kotlin(kotlinKapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(data))
    implementation(Kotlin.stdlib)
    implementAll(Network.components)
    implementation(DI.dagger)
    implementation(Coroutines.core)

    kapt(DI.AnnotationProcessor.dagger)
    kapt(Network.AnnotationProcessor.moshi)
}
