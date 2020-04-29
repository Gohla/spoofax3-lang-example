plugins {
  id("org.metaborg.gradle.config.root-project") version "0.3.21"
  id("org.metaborg.gitonium") version "0.1.2"

  // Set versions for plugins to use, only applying them in subprojects (apply false here).
  id("org.metaborg.spoofax.compiler.gradle.spoofaxcore.language") version "0.1.2" apply false
  id("de.set.ecj") version "1.4.1" apply false
  id("org.metaborg.coronium.bundle") version "0.2.2" apply false
  id("org.metaborg.coronium.embedding") version "0.2.2" apply false
  id("biz.aQute.bnd.builder") version "5.0.1" apply false
  id("org.jetbrains.intellij") version "0.4.18" apply false
}

subprojects {
  metaborg {
    configureSubProject()
  }
}

tasks.register("runCli") {
  group = "mod"
  dependsOn(":mod.cli:run")
}
//tasks.register("runEclipse") {
//  group = "mod"
//  dependsOn(":mod.eclipse:run")
//}
tasks.register("runIntelliJ") {
  group = "mod"
  dependsOn(":mod.intellij:runIde")
}
