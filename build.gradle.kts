import java.net.URI

plugins {
    id("java")
    id("maven-publish")
}

group = "org.example"
version = "1.0-SNAPSHOT"

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = URI(/* str = */ "https://maven.pkg.github.com/n1k1t0n/workflow-test")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }

    publications {
        register("workflow-pub-test", MavenPublication::class) {
            from(components["java"])
            artifact(tasks.named("jar"))
            groupId = "project.group"
            artifactId = "project.name"
            version = "project.version"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}