#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
COPY --from=build ${JAR_FILE} tutorial.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","tutorial.jar"]