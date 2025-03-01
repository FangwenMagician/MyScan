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
        maven {
            url = uri("https://mvn.cloud.alipay.com/nexus/content/repositories/open/")
            name = "alipay"
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://mvn.cloud.alipay.com/nexus/content/repositories/open/")
            name = "alipay"
        }
    }
}

rootProject.name = "MyScan"
include(":app")
