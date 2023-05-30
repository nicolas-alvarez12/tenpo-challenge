#
# Build stage
#
FROM maven:3.9.0 AS build
COPY src /home/tenpo-challenge/app/src
COPY pom.xml /home/tenpo-challenge/app
RUN mvn -f /home/tenpo-challenge/app/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:11
COPY --from=build /home/tenpo-challenge/app/target/app.jar /usr/local/lib/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]