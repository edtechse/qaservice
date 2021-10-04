#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
RUN mvn -f /home/app/pom.xml verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=qna-api -Dsonar.login=e15f79043485111edbdff3e1df560c0f67088a9b

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/qaservice-1.0-SNAPSHOT.jar /usr/local/lib/qaservice.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/local/lib/qaservice.jar"]
