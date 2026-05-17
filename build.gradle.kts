plugins {
    java
    id("fabric-loom") version "1.7-SNAPSHOT"
}

version = "1.0.0"
group = "com.example.addon"

repositories {
    mavenCentral()
    maven {
        name = "Fabric"
        url = uri("https://maven.fabricmc.net/")
    }
    maven {
        name = "Mojang"
        url = uri("https://libraries.minecraft.net/")
    }
    maven {
        name = "Meteor Releases"
        url = uri("https://maven.meteordev.org/releases")
    }
    maven {
        name = "Meteor Snapshots"
        url = uri("https://maven.meteordev.org/snapshots")
    }
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/releases/")
    }
}

dependencies {
    minecraft("com.mojang:minecraft:1.21.1")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.15.11")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.102.0+1.21.1")

    // Changed to modImplementation to force Loom to map Meteor Client to the environment
    modImplementation("meteordevelopment:meteor-client:0.5.8-SNAPSHOT")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}