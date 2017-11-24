# Vagrant + Puppet + Django

## Description

Project configures Django server with MySQL database.

Credentials for machine, Django project name and other parameters
are stored in configfile: `config.yaml`

Vagrantfile uses puppet to configure everything

Django project must be stored in the root directory

Python requirements must be stored here: `manifests/files/virtualenv`

## How to use

* Clone this repo
* Go to `denis-koptev` folder
* Run `vagrant up --provision`
* Wait until build finishes
* Ssh to machine: `vagrant ssh`
* Go to folder: `cd ../user/virtualenvs/test_site.com`
* Become root: `sudo su`
* Enter virtualenv: `source env/bin/activate`
* Install requirements: `pip3 install -r requirements.txt`
* Apply migrations for DB: `python3 test_site/manage.py migrate`
* Find out your VM IP: `ifconfig`
* Run server: `python3 test_site/manage.py runserver <vm ip>:80`
* Host browser: `localhost:8090`

## What happens

* Puppet installs MySql and creates all credentials and new DB
* django project will be mounted
* Virtual environment for Python libs will be created and mounted