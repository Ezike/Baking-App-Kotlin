import ProjectLib.domain

plugins {
    kotlinLibrary
}

dependencies {
    implementation(project(domain))
}
