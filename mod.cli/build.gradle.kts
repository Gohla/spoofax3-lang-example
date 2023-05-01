plugins {
  `java-library`
  id("org.metaborg.spoofax.compiler.gradle.cli")
  id("com.palantir.graal") version("0.12.0")
}

languageCliProject {
  adapterProject.set(project(":mod"))
}

tasks.withType<Jar> {
  duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

graal {
  javaVersion("11")
  graalVersion("22.3.2")

  mainClass("org.example.mod.cli.Main")
  outputName("mod")

  // Don't generate fallback image. Fail if native image can't be generated.
  option("--no-fallback")
  // Instead of failing to build the native image if something is not supported, fail at runtime instead. If the failing
  // part is never used at runtime, the native image is still usable.
  option("--report-unsupported-elements-at-runtime")
  // Specify classes to initialize at build time. https://www.graalvm.org/latest/reference-manual/native-image/guides/specify-class-initialization/
  option("--initialize-at-build-time=org.example.mod,mb,org.metaborg.util,org.strategoxt,org.spoofax.terms,org.spoofax.interpreter.terms,io.usethesource.capsule,com,org.slf4j,dagger,dagger.internal.InstanceFactory,picocli,javax.xml,jdk.xml")
  option("--initialize-at-run-time=org.spoofax.jsglr.io.SGLR") // SGLR creates a thread at class initialization, so it must be initialized at runtime.
  option("-H:TraceClassInitialization=true")
  option("-H:+ReportExceptionStackTraces")
  // Specify which resources (such as the parse table) to include into the native image. https://www.graalvm.org/latest/reference-manual/native-image/dynamic-features/Resources/
  option("-H:IncludeResources=org/example/mod/.*")
  // Specify dynamic proxies. https://www.graalvm.org/latest/reference-manual/native-image/dynamic-features/DynamicProxy/
  option("-H:DynamicProxyConfigurationFiles=src/main/gni/proxy.json")
  // Specify reflection. https://www.graalvm.org/latest/reference-manual/native-image/dynamic-features/Reflection/#manual-configuration
  option("-H:ReflectionConfigurationFiles=src/main/gni/reflection.json")
}

tasks {
  // Disable currently unused distribution tasks.
  distZip.configure { enabled = false }
  distTar.configure { enabled = false }
  startScripts.configure { enabled = false }
}
