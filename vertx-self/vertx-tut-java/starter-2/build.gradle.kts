import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.nghia.vertx"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.4.0"
val junitJupiterVersion = "5.9.1"

//val mainVerticleName = "com.nghia.vertx.starter_2.MainVerticle"
val mainVerticleName = "com.nghia.vertx.inaction.hello.HelloVertical"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation("io.netty:netty-all:4.1.89.Final")
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-core")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-infinispan")
  implementation("io.vertx:vertx-config")
  implementation("io.vertx:vertx-config-kubernetes-configmap")


  implementation("org.slf4j:slf4j-api:2.0.7")
  implementation ("org.flywaydb:flyway-core:9.19.4")


//  implementation("io.vertx:vertx-web-validation")
//  implementation("io.vertx:vertx-auth-jwt")
//  implementation("io.vertx:vertx-pg-client")
//  implementation("io.vertx:vertx-json-schema")
//  implementation("io.vertx:vertx-web-api-contract")
//  implementation("com.ongres.scram:client:2.1")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}
//
//tasks.create<JavaExec>("run") {
//  main = project.properties.getOrDefault("mainClass", "chapter2.hello.HelloVerticle") as String
//  classpath = sourceSets["main"].runtimeClasspath
//  systemProperties["vertx.logger-delegate-factory-class-name"] = "io.vertx.core.logging.SLF4JLogDelegateFactory"
//}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}

