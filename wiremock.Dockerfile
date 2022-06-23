# This is a generic Dockerfile for adding wiremock mapping and file resources to a wiremock image.
# All that is required is that a 'mappings' directory and '__files' directory exist in 'src/main/resources/wiremock'.
# Alternatively, these directories can be placed in 'src/test/resources/wiremock', with the Maven Resource lugin configured to capture them.
FROM rodolpheche/wiremock:2.31.0-alpine@sha256:c848ad9eb6bb028c8a364e55480793da10a26bd2c7ea3f0e28a311a22b2a0c04

ARG exposedPort=8445
ENV exposedPortEnv=$exposedPort

# Add our wiremock mapping and response files to wiremocks default run path.
ADD src/test/resources/wiremock/mappings/ /home/wiremock/mappings/

EXPOSE $exposedPort

CMD ["--port","8445","--verbose"]

# Run wget at a regular interval to check that the service is healthy.
HEALTHCHECK --interval=10s CMD ["sh", "-c", "wget --no-check-certificate http://localhost:8445/__admin/mappings -O /dev/null"]