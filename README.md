# Spring Boot + Docker + AWS Beanstalk

Simple hello world application compiled as docker container running on AWS Elasticbeanstalk

## Build

`./gradlew clean build`

## Build Docker

`./gradlew clean buildDocker`

## Run

`java -jar build/libs/spring-boot-docker-beanstalk.jar`

## Run Docker

Run the spring boot app locally

`docker run -p 8080:8080 -t springboot-docker-beanstalk`

## Tag the Docker image

Tag the docker image and point at remote registry

`docker tag springboot-docker-beanstalk <accountid>.dkr.ecr.us-west-2.amazonaws.com/springboot-docker-beanstalk`

## Docker Login to AWS ECR

Give docker permissions to access AWS ECR

`aws ecr get-login`

Run the output from the previous command

`docker login -u AWS -p <password> -e none https://<accountid>.dkr.ecr.us-west-2.amazonaws.com

## Create Repository

Create the repository in the ECR for our application.

`aws ecr create-repository --repository-name springboot-docker-beanstalk`

## Push to AWS ECR

Push the docker image to ECR.

`docker push <accountid>.dkr.ecr.us-west-2.amazonaws.com/springboot-docker-beanstalk:latest`

Ensure your image is in the repository.

`aws ecr describe-images --registry-id <registry-id> --repository-name springboot-docker-beanstalk`

## Create the Beanstalk Application

Initialize the project with AWS CLI. It should ask what kind of environment you want to create. Select Docker single instance.

`eb init`

This will ask you a series of questions as shown below, and the output should create a directory `.ebextensions`

```
Select a default region
1) us-east-1 : US East (N. Virginia)
2) us-west-1 : US West (N. California)
3) us-west-2 : US West (Oregon)
4) eu-west-1 : EU (Ireland)
5) eu-central-1 : EU (Frankfurt)
6) ap-south-1 : Asia Pacific (Mumbai)
7) ap-southeast-1 : Asia Pacific (Singapore)
8) ap-southeast-2 : Asia Pacific (Sydney)
9) ap-northeast-1 : Asia Pacific (Tokyo)
10) ap-northeast-2 : Asia Pacific (Seoul)
11) sa-east-1 : South America (Sao Paulo)
12) cn-north-1 : China (Beijing)
13) us-east-2 : US East (Ohio)
14) ca-central-1 : Canada (Central)
15) eu-west-2 : EU (London)
(default is 3): 3

Select an application to use
1) retail-dev
2) trip-lookup
3) ENS Delivery
4) DPPI
5) sabre-proxy-api
6) [ Create new Application ]
(default is 6): 6 

Enter Application Name
(default is "springboot-docker-beanstalk"): 
Application springboot-docker-beanstalk has been created.

Select a platform.
1) Node.js
2) PHP
3) Python
4) Ruby
5) Tomcat
6) IIS
7) Docker
8) Multi-container Docker
9) GlassFish
10) Go
11) Java
12) Packer
(default is 1): 7

Select a platform version.
1) Docker 1.12.6
2) Docker 1.11.2
3) Docker 1.9.1
4) Docker 1.7.1
5) Docker 1.6.2
6) Docker 1.5.0
(default is 1): 1
Note:
 Elastic Beanstalk now supports AWS CodeCommit; a fully-managed source control service. To learn more, see Docs: https://aws.amazon.com/codecommit/
Do you wish to continue with CodeCommit? (y/n) (default is n): n
Do you want to set up SSH for your instances?
(y/n): n
```

## Create Environment

This will create a new environment where the docker container will be deployed

`eb create`

## Deploy the Application

In order to deploy the application to beanstalk you need to provide the sou
The docker image

`zip -r springboot-docker-beanstalk.zip Dockerrun.aws.json`

Go to the beanstalk GUI and deploy the application via the Upload button.

Use the `eb logs` command to see if the application started

```
-------------------------------------
/var/log/eb-docker/containers/eb-current-app/8bfe0a90e7d5-stdouterr.log
-------------------------------------
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.3.RELEASE)

2017-05-31 15:07:00.007  INFO 5 --- [           main] hello.HelloApplication                   : Starting HelloApplication on 8bfe0a90e7d5 with PID 5 (/app.jar started by root in /)
2017-05-31 15:07:00.032  INFO 5 --- [           main] hello.HelloApplication                   : No active profile set, falling back to default profiles: default
2017-05-31 15:07:00.466  INFO 5 --- [           main] ationConfigEmbeddedWebApplicationContext : Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@4b9af9a9: startup date [Wed May 31 15:07:00 GMT 2017]; root of context hierarchy
2017-05-31 15:07:09.654  INFO 5 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat initialized with port(s): 8080 (http)
2017-05-31 15:07:09.713  INFO 5 --- [           main] o.apache.catalina.core.StandardService   : Starting service Tomcat
2017-05-31 15:07:09.719  INFO 5 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.14
2017-05-31 15:07:10.078  INFO 5 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-05-31 15:07:10.080  INFO 5 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 9625 ms
2017-05-31 15:07:10.609  INFO 5 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-05-31 15:07:10.627  INFO 5 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-05-31 15:07:10.630  INFO 5 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-05-31 15:07:10.631  INFO 5 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-05-31 15:07:10.632  INFO 5 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-05-31 15:07:11.740  INFO 5 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@4b9af9a9: startup date [Wed May 31 15:07:00 GMT 2017]; root of context hierarchy
2017-05-31 15:07:12.118  INFO 5 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/]}" onto public java.lang.String hello.HelloApplication.index()
2017-05-31 15:07:12.132  INFO 5 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-05-31 15:07:12.134  INFO 5 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-05-31 15:07:12.254  INFO 5 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]

```

## View Application

Visit the url http://<appname>.us-west-2.elasticbeanstalk.com/

## Useful docs

https://github.com/hopsoft/relay/wiki/How-to-Deploy-Docker-apps-to-Elastic-Beanstalk

https://spring.io/guides/gs/spring-boot-docker/

http://docs.aws.amazon.com/cli/latest/reference/ecr/get-login.html

http://docs.aws.amazon.com/elasticbeanstalk/latest/dg/eb3-create.html


