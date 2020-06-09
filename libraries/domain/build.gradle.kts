import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Kotlin

plugins {
    kotlin
    kotlin(kotlinKapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Kotlin.stdlib)
    implementation(Coroutines.core)
    implementation(DI.dagger)

    kapt(DI.AnnotationProcessor.dagger)
}
