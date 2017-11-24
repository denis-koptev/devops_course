# Gradle + Java App

## Description

This project contains a small Java application aimed to create AvlTree
and print it. Also, this app can be modified in different ways.

There is `build.gradle` configuration file that replaces/modifies some
standard build tasks with some new functionality and creates new tasks.
For example: for packaging all app products to ZIP or tracking test results

## Main tasks

* `compileJava` - Compiles main Java source.
* `compileTestJava` - Compiles test Java source.
* `createJar` - Task generates JAR executable from .class products
* `execApp` - Task executes application using .class products
* `packZip` - Task generates ZIP archieve with classes, jars and libs
* `processResources` - Processes main resources.
* `processTestResources` - Processes test resources.
* `setWrapper` - Task generates gradle wrapper
* `startScripts` - Creates OS specific scripts to run the project as a JVM application.

## How to run

### Examples

Command for testing:
	
	#gradle_demo\denis-koptev>gradle test

Output:

	> Task :test
	Executing test equals [AvlTreeTest] with result: SUCCESS
	Executing test toArray [AvlTreeTest] with result: SUCCESS
	Executing test remove [AvlTreeTest] with result: SUCCESS
	Executing test size [AvlTreeTest] with result: SUCCESS
	Executing test clear [AvlTreeTest] with result: SUCCESS
	Executing test iterator [AvlTreeTest] with result: SUCCESS
	Executing test add_contains [AvlTreeTest] with result: SUCCESS
	Executing test isEmpty [AvlTreeTest] with result: SUCCESS

Let's create wrapper

	#gradle_demo\denis-koptev>gradle setWrapper
	#gradle_demo\denis-koptev>gradlew run

Results:

	:run
	-44 -42 -31 -29 -28 -19 -16 -7 0 14 18 21 27 31 36 39 41 48
	-44 -42 -31 -29 -28 -19 -16 -7 0 14 18 21 27 31 36 39 41 48
	BUILD SUCCESSFUL

Don't forget to clean products after all: `gradle clean`