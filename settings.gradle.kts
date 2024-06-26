pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "AndroidPractiseTraining"
include(":app")
include(":notificationsdemo")
include(":fragmentdemo")
include(":broadcastdemo")
include(":savedatedemo")
include(":experimentjump")
include(":mediaplayer")
include(":myservice")
