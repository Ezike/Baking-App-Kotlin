import Dependencies.Coroutines
import Dependencies.Kotlin

plugins {
    kotlinLibrary
}

dependencies {
    implementation(Kotlin.stdlib)
    implementation(Coroutines.core)
}
