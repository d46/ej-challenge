FROM ubuntu:latest

WORKDIR /ej-ui
COPY . /ej-ui

RUN apt-get update
RUN apt-get install curl -y
RUN curl -sL https://deb.nodesource.com/setup_7.x | bash - && apt-get install nodejs -y
RUN npm i webpack -g
RUN npm i
RUN npm start
EXPOSE 8080:8080

