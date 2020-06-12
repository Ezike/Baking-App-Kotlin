import Dependencies.Network
import ProjectLib.data

plugins {
    kotlinLibrary
    kotlin(kotlinKapt)
}

dependencies {
    implementation(project(data))
    implementAll(Network.components)
    kapt(Network.AnnotationProcessor.moshi)
}
