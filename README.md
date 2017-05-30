# Spring Boot + Docker + AWS Beanstalk

Simple hello world application compiled as docker container running on AWS Elasticbeanstalk

## Build

`./gradlew clean build`

## Build Docker

`./gradlew clean buildDocker`

## Run

`java -jar build/libs/spring-boot-docker-beanstalk.jar`

## Run Docker

`docker run -p 8080:8080 -t springboot-docker-beanstalk`
