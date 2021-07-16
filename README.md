# spoofax3-lang-example

An example project demonstrating how to create a language project in Spoofax 3.

## Building and running

To build and test everything, run:

```
./gradlew buildAll
```

To start the CLI, run:

```
./gradlew runCli
```

To start the Eclipse plugin, run:

```
./gradlew runEclipse
```

To start the IntelliJ plugin, run:

```
./gradlew runIntelliJ
```

## Components

This Spoofax 3 language project consists of the following sub-projects:

### mod

The language definition for the "mod" language, configured via `spoofaxc.cfg`.
The `src` directory contains the language definition in SDF3, ESV, Statix, and Stratego, along with an example command task in `src/main/java/mb/mod/task/ModShowScopeGraph.java`, and some basic tests in `src/main/test`.

### mod.cli

A command-line interface for the "mod" language, based on the `mod` project.

### mod.eclipse/mod.eclipse.externaldeps

An Eclipse plugin for the "mod" language, based on the `mod` project.

### mod.intellij

An IntelliJ plugin for the "mod" language, based on the `mod` project.
