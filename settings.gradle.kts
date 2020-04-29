rootProject.name = "spoofax3-lang-example"

pluginManagement {
  repositories {
    maven("https://artifacts.metaborg.org/content/groups/public/")
  }
}

include("mod.spoofax2")
include("mod")
include("mod.spoofax")
include("mod.cli")
/*
Eclipse plugin is disabled for now: the Coronium Gradle plugin does not properly export its configurations when
published, so a dependency to a published version does not work properly.
*/
//include("mod.eclipse.externaldeps")
//include("mod.eclipse")
include("mod.intellij")
