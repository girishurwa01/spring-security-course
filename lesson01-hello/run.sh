#!/bin/zsh
# Starts the app. Stop it with Ctrl+C.
cd "$(dirname "$0")"
~/.m2/wrapper/dists/apache-maven-3.10.1/987df3ef/bin/mvn -q spring-boot:run
