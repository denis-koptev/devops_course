## DevOps lection 10.11.17 

# Builders (Maven, Gradle)

## Apache Maven

* Maven Fundamentals

### Functional

* Recreate build for any environment
* Download dependency, pull items
* With IDE or standalone
* Good for build tools (Jenkins)
* Based on XML

### Ant

* Designed to be crossplatform
* Agile enough
* __BUT__ it is not a build tool as much as a scripting tool (requires a lot of configuration info)

Example

	In Ant we need to write `clean` actions by hands
	In Maven these actions are already implemented

### Maven

* Maven has a lot of plugins for it
* Allows us to track versions
* Dependencies can be added to pom.xml file

### Structure

	HelloWorld 							<- project root
		src
			main
				java
					helloworld.java
		pom.xml 						<- build file
		target							<- folder for build results
			classes
			maven-archiever
			surefire
			test-classes
			<name>-<version>.jar

### Dependencies

	<dependencies>
		<dependency>
			<groupId> commons-lang </groupId>
			<artifactId> commons-lang </artifactId>
			<version> 2.0 </version>
		</dependency>
	</dependencies>

Everything downloaded is stored here: `C:\Users\<username>\.m2\...`

### Commands

* `clean` - deletes target dir and generated resources
* `compile`
* `package`
* `install`
* `deploy`

We can call commands one straight after another: `mvn clean package`

### Versions of project

* SNAPSHOT - while developing
* Milestone
* Release Candidate (rc)
* Release (release, final)

### Dependency repositories

	<repositories>
		<repository>
			<id>...</id>
			<name>...</name>
			<url>https://repo-springsource/libs-snapshot</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<releases>
				<enabled>false</enabled>
			</releases>
		<repository>
	<repositories>

### Plugins

	<plugin>...</plugin>

Example: maven-compiler-plugin
Allows us to specify JAR version, available memory, etc

Another plugin: maven-jar-plugin
Allows us to choose files which will be packaged to jar, etc

### Tools

There are viewers for pom files (simplify writing and reading them)

## Gradle

File: build.gradle

Standard builder for Android

Results: in `build` folder (`target` in Maven)

### Tasks

In Gradle we can define tasks

	def projectVersion =1.0
	Task Task1 {
		description "..."
		doLast {
			println "..."
		}
	}
	task "Task2"
	task("Task3")
	task Task4
	Task4.description = "..."
	Task4.doLast {println "It is Task4, version $projectVersion"}
	Task4.dependsOn Task1 // Task1 will be run first

### Typed Tasks

	Task copyTask (type: Copy) {
		from 'src'
		to '...'
	}

### Project structure

In case of non-standard project structure we can specify folders and etc by hands

### Multi-project builds

`settings.gradle` file - here we can specify different pom files

## Heroku

For Python, Java, JS and etc

### HW

Deploy app and provide the link in Telegram

## Mesos, Marathone

There are several VMs
There are Mesos + Scheduler for all VMs
If we have `job offer` we can send it to scheduler an it'll choose propper VM for this job

## Sonarcloud

Static code analysis
Allows to make different metrics and save statistics
