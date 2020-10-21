FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/comexport-api-core.jar
WORKDIR /app
ENTRYPOINT java -jar comexport-api-core.jar