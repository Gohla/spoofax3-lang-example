import mb.spoofax.compiler.gradle.spoofaxcore.*
import mb.spoofax.compiler.spoofaxcore.*
import mb.spoofax.compiler.util.*

plugins {
  id("org.metaborg.spoofax.compiler.gradle.spoofaxcore.language")
}

dependencies {
  testImplementation("org.metaborg:log.backend.slf4j")
  testImplementation("org.slf4j:slf4j-simple:1.7.30")
  testCompileOnly("org.checkerframework:checker-qual-android")
}

spoofaxLanguageProject {
  settings.set(LanguageProjectSettings(
    shared = Shared.builder()
      .name("Mod")
      .defaultBasePackageId("mb.mod"),

    parser = ParserCompiler.LanguageProjectInput.builder()
      .startSymbol("Start"),
    styler = StylerCompiler.LanguageProjectInput.builder(),
    completer = CompleterCompiler.LanguageProjectInput.builder(),
    strategoRuntime = StrategoRuntimeCompiler.LanguageProjectInput.builder()
      .enableNaBL2(false)
      .enableStatix(true)
      .copyCTree(true)
      .copyClasses(false)
      .copyJavaStrategyClasses(false),
    constraintAnalyzer = ConstraintAnalyzerCompiler.LanguageProjectInput.builder()
      .multiFile(true),

    builder = LanguageProjectCompiler.Input.builder()
      .languageSpecificationDependency(GradleDependency.project(":mod.spoofax2"))
  ))
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
