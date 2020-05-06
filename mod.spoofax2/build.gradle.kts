plugins {
  id("org.metaborg.spoofax.gradle.langspec") version "0.2.4"
  id("de.set.ecj") // Use ECJ to speed up compilation of Stratego's generated Java files.
  `maven-publish`
}

spoofax {
  metaborgVersion = "2.5.8"
  createPublication = true
}

ecj {
  toolVersion = "3.20.0"
}
