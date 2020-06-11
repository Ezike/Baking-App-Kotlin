import Dependencies.Coroutines
import Dependencies.DI
import Dependencies.Kotlin

plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Kotlin.stdlib)
    implementation(Coroutines.core)
    implementation(DI.javaxInject)
}
