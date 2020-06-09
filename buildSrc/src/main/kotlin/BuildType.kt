interface BuildType {

    companion object {
        const val DEBUG: String = "debug"
        const val RELEASE: String = "release"
    }

    val isMinifyEnabled: Boolean
    val isTestCoverageEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled: Boolean = false
    override val isTestCoverageEnabled: Boolean = true

    const val applicationIdSuffix: String = ".debug"
    const val versionNameSuffix: String = "-DEBUG"
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled: Boolean = true
    override val isTestCoverageEnabled: Boolean = false
}
