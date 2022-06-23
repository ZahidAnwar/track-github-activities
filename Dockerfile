FROM adoptopenjdk/openjdk11:jre-11.0.14.1_1-alpine@sha256:e90b99974f0a423a54b398b998b6eed031916e621f8514cbf2507c79f2b5db17
LABEL maintainer="team.lead@tui.com"

ENV APPLICATION_USER=app \
    APPLICATION_GROUP=app_group \
    APPLICATION_PATH=/app.jar

EXPOSE 8082 9321

COPY wrapper.sh /wrapper.sh
# RUN chmod 555 /wrapper.sh

RUN addgroup --gid 10001 ${APPLICATION_GROUP} && \
    adduser --uid 10002 --gecos "" --disabled-password --ingroup ${APPLICATION_GROUP} ${APPLICATION_USER} && \
    chmod 555 /wrapper.sh

ENTRYPOINT ["/wrapper.sh"]

COPY target/track-github-activities-*.jar ${APPLICATION_PATH}
