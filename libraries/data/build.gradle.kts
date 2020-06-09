import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Kotlin
import ProjectLib.domain

plugins {
    kotlin
    kotlin(kotlinKapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(domain))
    implementation(Kotlin.stdlib)
    implementation(DI.dagger)
    implementation(Coroutines.core)

    kapt(DI.AnnotationProcessor.dagger)
}
