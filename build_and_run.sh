#!/bin/sh
#set -ex

if type -p java >> /dev/null; then
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    _java="$JAVA_HOME/bin/java"
else
    echo "no java installed"
    exit -1

fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    if [[ "$version" < "11" ]]; then
        echo java version "$version"
        echo required version is less than 11
        exit -1
    fi
fi

./gradlew build
if [ $? -eq 0 ]; then
    java -jar ./build/libs/cronparser-0.1.0.jar "$1"
else
    echo BUILD FAILED. Fix it to run the command.
    exit -1
fi
