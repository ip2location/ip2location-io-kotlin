plugins {
    alias(libs.plugins.kotlin.jvm)
    id("com.vanniktech.maven.publish") version "0.36.0"
    id("org.jetbrains.dokka") version "2.1.0"
    `java-library`
    id("signing")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.apache.commons:commons-csv:1.14.1")
    api("com.google.code.gson:gson:2.13.2")
    implementation("org.apache.commons:commons-lang3:3.20.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.10.2")
}

mavenPublishing {
    configureBasedOnAppliedPlugins()
    coordinates("com.ip2location", "ip2location-io-kotlin", "1.2.0")
    publishToMavenCentral()
    signAllPublications()

    pom {
        name.set("IP2Location.io Kotlin")
        description.set("IP2Location.io Kotlin Library allows user to query for an enriched data set based on IP address and provides WHOIS lookup api that helps users to obtain domain information.")
        inceptionYear.set("2026")
        url.set("https://github.com/ip2location/ip2location-io-kotlin")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("ip2location")
                name.set("IP2Location")
                email.set("support@ip2location.com")
            }
        }
        scm {
            connection.set("scm:git:github.com/ip2location/ip2location-io-kotlin.git")
            developerConnection.set("scm:git:ssh://github.com/ip2location/ip2location-io-kotlin.git")
            url.set("https://github.com/ip2location/ip2location-io-kotlin")
        }
    }
}

signing {
    // Call the 'gpg' command on Windows 11 so Kleopatra pops up.
    useGpgCmd()

    // This ensures we only sign when we are actually publishing
    val isPublishing = gradle.taskGraph.allTasks.any { it.name.contains("publish", ignoreCase = true) }
    setRequired(isPublishing)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
