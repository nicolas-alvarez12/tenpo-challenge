#
# Build stage
#
FROM maven:3.6.0 AS build
COPY src /home/tenpo-challenge/external/src
COPY pom.xml /home/tenpo-challenge/external
RUN mvn -f /home/tenpo-challenge/external/pom.xml clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM openjdk:11
COPY --from=build /home/tenpo-challenge/external/target/external.jar /usr/local/lib/external.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/external.jar"]