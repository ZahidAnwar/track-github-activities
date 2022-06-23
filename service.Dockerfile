FROM adoptopenjdk/openjdk11:jre-11.0.14.1_1-alpine@sha256:e90b99974f0a423a54b398b998b6eed031916e621f8514cbf2507c79f2b5db17
USER root

ARG springProfile=docker-integration-tests
ARG exposedPort=8082
ARG serviceName=track-github-activities
ARG protocol=http

ENV springProfileEnv=$springProfile
ENV exposedPortEnv=$exposedPort
ENV serviceNameEnv=$serviceName
ENV protocolEnv=$protocol


WORKDIR /service

COPY target/${serviceName}-*.jar /service/${serviceName}.jar

EXPOSE $exposedPort

ENTRYPOINT java -Dspring.profiles.active=$springProfileEnv -jar ./${serviceNameEnv}.jar

# Run wget at a regular interval to check that the service is healthy.
HEALTHCHECK --interval=5s CMD ["sh", "-c", "wget --no-check-certificate http://localhost:9321/actuator/health -O /dev/null"]
