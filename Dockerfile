FROM maven:3.6.1-jdk-8 as build
WORKDIR /joboonja
COPY src /joboonja/src
COPY pom.xml /joboonja
RUN mvn clean package

FROM openjdk:8-alpine
WORKDIR /joboonja
COPY --from=build /joboonja/target/joboonja.jar /joboonja
COPY docker-entrypoint.sh /joboonja
CMD ["./docker-entrypoint.sh"]