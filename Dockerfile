FROM openjdk:8-alpine

WORKDIR /joboonja
COPY target/joboonja.jar /joboonja
COPY docker-entrypoint.sh /joboonja
CMD ["./docker-entrypoint.sh"]