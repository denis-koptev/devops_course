# Docker + Jenkins + Django server

## Quick run

### Installation

* Pull image from my Docker Hub: `docker pull deniskoptev/py_jenkins` (*Note: it is heavy*)
* `docker run -p 8080:8080 -p 50000:50000 -d deniskoptev/py_jenkins`
* Host browser: `localhost:8080`

    *Note: it may start slowly, just reload page*

* Credentials: admin:admin

### Testing

__Pipeline__

There will be Pipeline job for Django-server testing.

Special plug-in `Warnings Plug-in` allows to see PyLint Test output in a smart view.

Just run new build and go to `PyLint Warnings` tab

__Freestyle__

Freestyle job runs tests without Jenkinsfile. 

It uses Plug-ins to form different reports from PyLint, PyFlakes, pep8 and code coverage.

## Works done

### To make data persistent

* I cloned official jenkins repo for docker
* Modified Dockerfile: `CMD mkdir -p` instead of `VOLUME`
* Builded new `jenkins_persistent` image
* It can be pulled from here: `docker pull deniskoptev/jenkins_persistent`

### Building Jenkins with Python

* Created new Dockerfile with jenkins_persistent as a source
* Dockerfile contains Python and Pip installation
* After that we can get new docker image: i.e. `py_jenkins`

### Setting up Jenkins

* Plugins installed:

        GitHub Branch Source, 
        Pipeline, 
        Pipeline: GitHub Groovy Libs, 
        Git, 
        Pipeline Stage View,
        Warnings (for PyLint testing)
        Cobertura (for code coverage)
        Violations (for PyLint, PyFlakes, pep8)
    
* Admin credentials

        Login: admin
        Password: admin
        
### Jobs

* Pipeline: 
        
    django_pipeline
    
    GitHub django project: https://github.com/denis-koptev/django_test
    
    Pipeline script from SCM: https://github.com/denis-koptev/jenkins_demo
    
* Freestyle

    dango_freestyle
    
