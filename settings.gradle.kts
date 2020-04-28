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
include("mod.eclipse.externaldeps")
include("mod.eclipse")
include("mod.intellij")
