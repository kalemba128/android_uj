FROM ubuntu:22.04

RUN apt-get update && apt-get install -y openjdk-8-jdk

RUN apt-get -y install kotlin

RUN apt-get update -y && apt-get install -y curl unzip

WORKDIR /opt
RUN curl -s https://downloads.gradle.org/distributions/gradle-8.4-bin.zip -o gradle.zip
RUN unzip gradle.zip
RUN rm gradle.zip

ENV GRADLE_HOME=/opt/gradle-8.4
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN apt-get install software-properties-common -y
RUN add-apt-repository ppa:deadsnakes/ppa

RUN apt update && apt install tzdata -y
ENV TZ="Europe/Warsaw"

RUN apt install python3.8 -y

RUN apt-get -y install libxerial-sqlite-jdbc-java

WORKDIR /app
COPY build.gradle /app/
CMD ["gradle","hello"]