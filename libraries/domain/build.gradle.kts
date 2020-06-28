import Dependencies.Test

plugins {
    kotlinLibrary
}

dependencies {
    testImplementation(Test.junit)
    testImplementation(Test.truth)
    testImplementation(Test.coroutinesTest)
}
