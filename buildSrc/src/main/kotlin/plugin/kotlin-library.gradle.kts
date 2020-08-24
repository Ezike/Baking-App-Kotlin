import Dependencies.Coroutines
import Dependencies.DI

plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Coroutines.core)
    implementation(DI.javaxInject)
}
