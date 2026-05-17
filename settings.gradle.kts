pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.meteordev.org/releases")
        maven("https://maven.meteordev.org/snapshots")
        maven("https://maven.terraformersmc.com/releases/")
        maven("https://libraries.minecraft.net/")
    }
}

rootProject.name = "curium-addon"