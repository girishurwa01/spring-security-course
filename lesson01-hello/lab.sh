#!/bin/zsh
# BCrypt lab. Run ./lab.sh with no arguments for help.

cd "$(dirname "$0")"
MVN=~/.m2/wrapper/dists/apache-maven-3.10.1/987df3ef/bin/mvn
if [ ! -f target/classpath.txt ]; then
  $MVN -q -o dependency:build-classpath -Dmdep.outputFile=target/classpath.txt >/dev/null || exit 1
fi
java -cp "$(cat target/classpath.txt)" tools/BcryptLab.java "$@"
