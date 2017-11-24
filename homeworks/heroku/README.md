# Heroku + Django web-server example

## Description

This project allows to deploy Django server and Postgresql automatically with Heroku

## Direct link

If everything is fine with my Heroku account you can access web-site via direct link

https://deniskoptev-test-site.herokuapp.com/

## How to run

### Automatically

[![Deploy](https://www.herokucdn.com/deploy/button.png)](https://dashboard.heroku.com/new?button-url=https%3A%2F%2Fgithub.com%2Fdenis-koptev%2Fdjango_test%2Ftree%2Fheroku&template=https%3A%2F%2Fgithub.com%2Fdenis-koptev%2Fdjango_test%2Ftree%2Fheroku)

### By hands

* Fork original repo: https://github.com/denis-koptev/django_test
* Sign in to your Heroku account: https://id.heroku.com/login
* Create new app and name it
* Deployment method: GitHub (need to connect to GitHub)
* Choose forked repo
* Use branch `heroku`
* Deploy :)

## Details

* Project automatically redeploys server after any push to repo
* Original project `django_test#master` was changed to simplify configuration

### Changes

* All Django applications are stored in repo root now
* `Procfile` added for Heroku configuration (describes start-up via `wsgi`)
* `app.json` added to provide automatical deployment (i.e.: via button)
* `requirements.txt` appended with tools for `Postgresql` and `wsgi`
