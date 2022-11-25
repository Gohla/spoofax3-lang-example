plugins {
  id("org.metaborg.spoofax.compiler.gradle.eclipse")
}

languageEclipseProject {
  adapterProject.set(project(":mod"))
}

mavenize {
  majorVersion.set("2021-03")
}
