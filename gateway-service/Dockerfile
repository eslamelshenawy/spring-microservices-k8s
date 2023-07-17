#FROM maven:3.8.5-openjdk-11 AS maven_build
#WORKDIR /build
#COPY pom.xml .
## create a layer with all of the Manven dependencies, first time it takes a while consequent call are very fast
##RUN mvn dependency:go-offline
#
#COPY pom.xml /tmp/
#
#COPY src /tmp/src/
#
#WORKDIR /tmp/
#
#RUN mvn package
#
##pull base image
#
#FROM eclipse-temurin:11
#
##expose port 8080
#EXPOSE 8080
#
##default command
#CMD java -jar /data/gateway-service-1.1.jar
#
##copy vmware to docker image from builder image
#
#COPY --from=maven_build /tmp/target/gateway-service-1.1.jar /data/gateway-service-1.1.jar


FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/gateway-service-1.1.jar"]
