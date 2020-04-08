plugins {
    java
    idea
    application
    kotlin("jvm") version "1.3.70"
}

repositories {
    mavenCentral()
    jcenter()
}

group = "com.github.ikovalyov"
version = "0.1"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}