FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-ubuntu
RUN apt-get update
RUN apt-get upgrade -y
RUN apt-get install curl -y
RUN apt-get install wget -y
RUN apt-get install vim -y

VOLUME /tmp
ARG JAR_FILE
ARG PROFILES
ENV SET_PROFILE="$PROFILES"
RUN mkdir /app
WORKDIR /app
COPY ${JAR_FILE} fantoo-app-api-SNAPSHOT.jar
COPY deploy/entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh
RUN mkdir /app/logs
RUN touch /app/logs/spring.log

EXPOSE 8080/tcp
ENTRYPOINT sh entrypoint.sh $SET_PROFILE