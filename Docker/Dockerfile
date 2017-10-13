FROM ubuntu
RUN apt-get update

#RUN apt-get install -y figlet
#CMD figlet -f script hello
#ENTRYPOINT ["figlet", "-f", "script"]
#CMD ["hello world"]

RUN apt-get install -y build-essential
COPY hello.c /
RUN make hello
CMD /hello