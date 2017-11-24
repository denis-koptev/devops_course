## DevOps lection 27.10.17

## HW

    Clone: docker_demo from DevOps-Course repo
    Write Dockerfile
    Base: Ubuntu, centos, alpine
    Commands: COPY, ADD
    Install curl, nginx, tomcat, apache
    Create account on Docker Hub, push docker image (public), describe installation in readme
    Additional: create docker-compose file allowing to run 2 apps (from one?)

## Sth more about Docker

* File Docker-compose.yml can be created

Example:

        www:
        build: www
        ports
            - 8000:5000
        links
            - redis

* Commands: `docker-compose rm`, `docker-compose kill` and etc.

# Jenkins

## Definitions

* CI (Continuous Integration)
* CD (Continuous Delivery)

*Integration and Delivery are NOT the Continuous Deployment*

* Testing: unit, integration, smoke tests, regression 

### CI

Push & pull per 1 day

* Problems

    Two people push into 1 branch: Merge (or Integration) conflicts
    Solution: Synchronize (push & pull) every day

### CD

It's not an automatical deployment and delivery to end-user
But it says that we must regulary have sth to deliver

## Systems

For this aims and for testing there are some systems:

* Jenkins
* Team City
* Travis

They can store and run builds for testing and post it for production delivery

## Work with Jenkins

Run tutorial can be found in official Jenkins repo

    docker run -p 8080:8080 -p 50000:50000 -d jenkins

Then with browser: localhost:8080

To unlock Jenkins we can

* Share `/var/jenkins_home`
* Or run in interractive mode and read it
* Or see log (password has been written to the log): `docker logs <id>`

Internet access required for plugin installation

Plugins:

* Pipeline & Pipeline Stage View
* Git

Then create new user

Create new job (Freestyle project, Pipeline and etc)

We're starting with freestyle (free configuration):
    
    * Parametrized build
        * Choise parameter: 
        Name: JDK
        Vars:
            1.7
            1.8
    * Credentials parameter:
        Name: creds
        Type: Username with password
        Default: [add new one]
    * Source code management
        Git: specify repo   
    * In git: add webhook in settings (to notify)

We can add another job: Pipeline

* Pipeline script example

        node(){
            stage("Stage 1"){
                sh 'echo hello'
            }
            stage("Stage 2"){
                sh 'echo goodbye'
            }
        }

We can create new node and run it with Java: `Manage Jenkins -> Manage Nodes`

Language Installation: 

* Plugins -> locale
* Manage Jenkins: Locale -> EN

## HW

* Fork repo
* Create Jenkins file
* Make pipeline - service for plugin build as in the [example](https://github.com/jenkinsci/robot-plugin)
* Plugin: role-based authorization strategy (install and configure)

*All HW can be found in Telegram*
