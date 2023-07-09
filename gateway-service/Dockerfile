FROM  openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway-service-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/gateway-service-1.1.jar"]
