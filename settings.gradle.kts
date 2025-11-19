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
        maven(url = "https://jitpack.io")
        maven {
            //Staging: yuvital-life-sdk-staging
            //Production: yuvital-life-sdk-production
            url = uri("https://dl.cloudsmith.io/basic/yuvital/yuvital-life-sdk-production/maven/")
            credentials {
                username = "token"
                password = providers.gradleProperty("yuvitalLifeSdkPassword").get()
            }
        }
    }
}

rootProject.name = "YuvitalLifeKotlinSample"
include(":app")
