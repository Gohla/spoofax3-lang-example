plugins {
  `java-library`
  id("org.metaborg.spoofax.compiler.gradle.intellij")
}

languageIntellijProject {
  adapterProject.set(project(":mod"))
}
