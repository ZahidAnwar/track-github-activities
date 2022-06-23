#!/usr/bin/env sh

set -eo pipefail

# Enables application to take PID 1 and receive SIGTERM sent by Docker stop command.
exec java -Djava.security.egd=file:/dev/./urandom -jar "${APPLICATION_PATH}"
