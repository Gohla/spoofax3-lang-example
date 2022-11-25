plugins {
  `java-library`
  id("org.metaborg.spoofax.compiler.gradle.cli")
}

languageCliProject {
  adapterProject.set(project(":mod"))
}

tasks.withType<Jar> {
  duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks {
  // Disable currently unused distribution tasks.
  distZip.configure { enabled = false }
  distTar.configure { enabled = false }
  startScripts.configure { enabled = false }
}
