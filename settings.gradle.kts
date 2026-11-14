
plugins {
    // Lets Gradle download the Java 21 toolchain if it is not installed locally
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// Repositories are declared once here, for every module. FAIL_ON_PROJECT_REPOS stops
// Individual build files from adding their own ( a common enterprise rule; point this at
// your Nexus/Artifactory mirror if you have one)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

rootProject.name = "HexagonalArchitecture"