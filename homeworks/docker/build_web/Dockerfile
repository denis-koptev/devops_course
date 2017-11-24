FROM ubuntu:latest

RUN apt-get update --yes
RUN apt-get upgrade --yes

# Python packages

RUN apt-get install --yes python3
RUN apt-get install --yes python3-pip
RUN pip3 install --upgrade pip

# Project sources

COPY django /django

# Set up MySQL

RUN apt-get install --yes libmysqlclient-dev
RUN apt-get install --yes python-dev

# Python dependencies for project

RUN pip3 install -r /django/requirements.txt

# Run server at start with script

COPY start_site.sh /
CMD ["/bin/bash", "start_site.sh"]