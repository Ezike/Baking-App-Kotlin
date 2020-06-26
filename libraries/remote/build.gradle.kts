import Dependencies.Network
import Dependencies.Test
import ProjectLib.data

plugins {
    kotlinLibrary
    kotlin(kotlinKapt)
}

dependencies {
    implementation(project(data))
    implementAll(Network.components)

    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.mockWebServer)

    kapt(Network.AnnotationProcessor.moshi)
}
