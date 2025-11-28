plugins {
    kotlin("jvm") version "2.1.0"
    `java-library`
    `maven-publish`
    signing
}

group = "io.github.jafcn09"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8)
    }
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "toggle-lite"
            from(components["java"])

            pom {
                name.set("ToggleLite")
                description.set("A minimal, zero-dependency feature flag library for Kotlin/JVM")
                url.set("https://github.com/jafcn09/kotlin-litle-flags")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("jafcn09")
                        name.set("Jhafet Canepa")
                        email.set("jafcn09@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/jafcn09/kotlin-litle-flags")
                    developerConnection.set("scm:git:ssh://github.com/jafcn09/kotlin-litle-flags")
                    url.set("https://github.com/jafcn09/kotlin-litle-flags")
                }
            }
        }
    }

    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://ossrh-staging-api.central.sonatype.com/service/local/staging/deploy/maven2/")
            credentials {
                username = project.findProperty("ossrhUsername") as String? ?: System.getenv("OSSRH_USERNAME")
                password = project.findProperty("ossrhPassword") as String? ?: System.getenv("OSSRH_PASSWORD")
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}
