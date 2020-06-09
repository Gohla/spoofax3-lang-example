rootProject.name = "spoofax3-lang-example"

pluginManagement {
  repositories {
    maven("https://artifacts.metaborg.org/content/groups/public/")
  }
}

if(org.gradle.util.VersionNumber.parse(gradle.gradleVersion).major < 6) {
  enableFeaturePreview("GRADLE_METADATA")
}

include("mod.spoofax2")
include("mod")
include("mod.spoofax")
include("mod.cli")
include("mod.eclipse.externaldeps")
include("mod.eclipse")
include("mod.intellij")
