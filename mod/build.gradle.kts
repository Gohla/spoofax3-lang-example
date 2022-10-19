plugins {
  `java-library`
  id("org.metaborg.spoofax.lwb.compiler.gradle.language")
}

val checkerFrameworkVersion = "3.16.0"
dependencies {
  compileOnly("org.checkerframework:checker-qual-android:$checkerFrameworkVersion")

  testImplementation("org.metaborg:spoofax.test:0.10.0")
  testCompileOnly("org.checkerframework:checker-qual-android:$checkerFrameworkVersion")
}

val junitVersion = "5.5.2"
dependencies {
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events(org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
    showExceptions = true
    showCauses = true
    showStackTraces = true
    exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
  }
}

tasks.register("compileDependenciesList") {
  project.configurations.compileClasspath.get().incoming.resolutionResult.allDependencies
    .filter { !it.isConstraint }
    .distinctBy { it.requested }
    .sortedBy { it.requested.displayName }
    .forEach { println(it.requested)  }
}
tasks.register("runtimeDependenciesList") {
  project.configurations.runtimeClasspath.get().incoming.resolutionResult.allDependencies
    .filter { !it.isConstraint }
    .distinctBy { it.requested }
    .sortedBy { it.requested.displayName }
    .forEach { println(it.requested)  }
}
