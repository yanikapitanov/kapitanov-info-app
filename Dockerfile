FROM openjdk:17-jre-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8111
ENTRYPOINT ["java","-jar","/app.jar"]
