# Django web-server simple example

## Installation

### For Linux

* Clone this repo
* Install python, pip and virtualenv

		apt-get install python3
		apt-get install python3-pip
		pip3 install virtualenv

* Create and activate virtualenv in repo root folder

		virtualenv env
		source env/bin/activate
		
* Install requirements: `pip3 install -r requirements.txt`
* Go to `test_site` folder
* Run server on default IP (localhost) and port (8000)

		python3 manage.py runserver

* Custom IP and port: `python3 manage.py runserver ip:port`

### For Windows

For Windows installation see [TobaccoFeed readme](https://github.com/denis-koptev/TobaccoFeed)
