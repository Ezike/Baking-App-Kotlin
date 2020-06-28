import Dependencies.Test
import ProjectLib.domain

plugins {
    kotlinLibrary
}

dependencies {
    implementation(project(domain))
    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
}
