pluginManagement {
    repositories {
        google {
            content {
                includeGroup("com.google.gms")
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
        maven {
            url = uri("https://company/com/maven2")
        }
        // Mapbox Maven repository
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
        }
        mavenLocal()
        flatDir {
            dirs("libs")
        }

    }
}

rootProject.name = "Practica 6 David Borja"
include(":app")
 