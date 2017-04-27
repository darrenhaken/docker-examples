FROM frolvlad/alpine-oraclejdk8:slim

#VOLUME pointing to "/tmp" because that is where a Spring Boot application creates working directories
# The effect is to create a temporary file on your host under "/var/lib/docker" and link it to the container under "/tmp".
# Optional step but gives benefits
VOLUME /tmp

ADD spring-app-0.1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
