#!/bin/zsh
# Usage:  ./hash.sh <password>
# Prints the BCrypt hash of <password>.
cd "$(dirname "$0")"
if [ -z "$1" ]; then echo "Usage: ./hash.sh <password>"; exit 1; fi
MVN=~/.m2/wrapper/dists/apache-maven-3.10.1/987df3ef/bin/mvn
if [ ! -f target/classpath.txt ]; then
  $MVN -q -o dependency:build-classpath -Dmdep.outputFile=target/classpath.txt >/dev/null || exit 1
fi
java -cp "$(cat target/classpath.txt)" tools/HashTool.java "$1"
