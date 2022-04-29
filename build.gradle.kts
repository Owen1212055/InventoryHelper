plugins {
    java
}

group = "com.owen1212055"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
}
