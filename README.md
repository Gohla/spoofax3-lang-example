# spoofax3-lang-example

An example project demonstrating how to create a language project in Spoofax 3.

## Building and running

To build everything, run:

```
./gradlew buildAll
```

To start the CLI, run:

```
./gradlew runCli
```

To start the IntelliJ plugin, run:

```
./gradlew runIntelliJ
```

## Components

This Spoofax 3 language project consists of the following sub-projects:

### mod.spoofax2

A simple language specification of the "mod" language in Spoofax 2.x. Spoofax 3 does not build language specifications yet, so we have to wrap a Spoofax 2.x language instead. 

### mod

A language project for the "mod" language, containing the basic building blocks such as a parser, syntax highlighter, Stratego runtime, and constraint analyzer. 
The Spoofax 3 compiler generates classes for these building blocks in `mod/build/generated/sources/spoofax/java`, which use the build artifacts from `mod.spoofax2`. 
The Spoofax 3 compiler is configured in `mod/build.gradle.kts`. 
The Spoofax 3 compiler also creates a 'lockfile' at `mod/spoofaxc.lock` which contains settings required for reproducible builds.
This project can be used as a very simple API to the "mod" language, with an example of that in the test directory `mod/src/test/java/mb/mod/`.

### mod.spoofax

A Spoofax 3 adapter project which adapts the `mod` project into a Spoofax 3 project with support for incrementality with PIE, and bindings to platforms such as CLI, Eclipse, IntelliJ, and more in the future.
The reason for the separation between `mod` and `mod.spoofax` is that `mod` is very simple and has very few dependencies, which may be desired in some settings.
This project wraps `mod` and adds dependency injection (with Dagger 2), support for incrementality and modularity with PIE tasks, and implements the `LanguageInstance` interface from Spoofax 3, which is used to bind to other platforms.
The Spoofax 3 compiler plugin again generates these classes for you in `mod.spoofax/build/generated/sources/spoofax/java`, and is configured in `mod.spoofax/build.gradle.kts`.
An example task to show the scope graph of a file is found at `mod.spoofax/src/main/java/mb/mod/spoofax/task/ModShowScopeGraph.java`.

### mod.cli

A command-line interface for the "mod" language, based entirely on the `mod.spoofax` project.

### mod.eclipse/mod.eclipse.externaldeps

An Eclipse plugin for the "mod" language, based entirely on the `mod.spoofax` project.
This currently does not work, as the `spoofax.eclipse` plugin has not been published properly

### mod.intellij

An IntelliJ plugin for the "mod" language, based entirely on the `mod.spoofax` project.
