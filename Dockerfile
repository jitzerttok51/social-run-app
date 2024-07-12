FROM alpine:latest

ADD build/libs/social-run-app-1.0.*.jar app.jar

EXPOSE 8080

RUN apk add openjdk21-jre-headless
ENTRYPOINT [ "java", "-jar", "/app.jar" ]