FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/prova.jar
WORKDIR /app
ENTRYPOINT java -jar prova.jar