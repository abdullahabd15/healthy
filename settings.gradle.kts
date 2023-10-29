pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Kotlin Technical Test"
include(":app")
include(":data")
include(":domain")
include(":ui")
include(":ui:auth")
include(":ui:main")
include(":ui:profile")
include(":shared")
