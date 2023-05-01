# spoofax3-lang-example

An example project demonstrating how to create a language project in Spoofax 3.

## Building and running

To build and test everything, run:

```
./gradlew buildAll
```

To start the CLI and parse a program, run:

```
./gradlew :mod.cli:run --args="parse <absolute path to mod file>"
```

To create a GraalVM Native Image for the CLI, run:

```
./gradlew :mod.cli:nativeImage
```

To start the Eclipse plugin, run:

```
./gradlew :mod.eclipse:runEclipse
```

To start the IntelliJ plugin, run:

```
./gradlew :mod.intellij:runIde
```

## Components

This Spoofax 3 language project consists of the following sub-projects:

### mod

The language definition for the "mod" language, configured via `spoofaxc.cfg`.
The `src` directory contains the language definition in SDF3, ESV, Statix, and Stratego, along with an example command task in `src/main/java/mb/mod/task/ModShowScopeGraph.java`, and some basic tests in `src/main/test`.

We extend the instance with `extend-instance = java org.example.mod.ModExtendInstance` in `spoofaxc.cfg` to specify CLI commands in `ModExtendInstance#getCliCommand`, as that is currently the only way to specify CLI commands.

### mod.cli

A command-line interface for the "mod" language, based on the `mod` project.

### mod.eclipse/mod.eclipse.externaldeps

An Eclipse plugin for the "mod" language, based on the `mod` project.

### mod.intellij

An IntelliJ plugin for the "mod" language, based on the `mod` project.
