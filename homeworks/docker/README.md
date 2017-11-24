# Docker: Django Server + MySQL Example

## Description

Project deploys small Django server connected to MySQL Database. Database has one table with students. Main page application queries students from DB and outputs the list into html table.
Database and server will be deployed on separate containers and then connected via common network. So, you need to build and run 2 images.

## Links with images

* [DB image](https://hub.docker.com/r/deniskoptev/djangomysql_db/)
* [Django image](https://hub.docker.com/r/deniskoptev/djangomysql_web/)

Or simply:

* `docker pull deniskoptev/djangomysql_db`
* `docker pull deniskoptev/djangomysql_web`

## Installation

Actually, these images can be easily built with the help of `docker-compose.yml` included in this repo.

### Steps:

* Clone this repo
* `docker-compose build db`
* `docker-compose build web`
* `docker-compose run db`
* Wait until container is fully launched
* `docker-compose run web`

*Note: database container must be launched first*

### Result

Web-site will be available on the host-machine. Just go to localhost:8000 in your host's browser.

Database will be available on 3306 port.

## Actions done

Docker-compose calls Dockerfile builds.

### Django

Dockerfile takes ubuntu:latest and installs all updates. Then it installs: python, pip for python; copies django project from host, installs requirements for django. Then sets up entrypoint: script for IP retrieving and server launching.

### MySQL

Dockerfile uses mysql:latest image, copies db-dump in sql format to the special dump directory in the image.

---

Denis Koptev, SPbSTU, 2017