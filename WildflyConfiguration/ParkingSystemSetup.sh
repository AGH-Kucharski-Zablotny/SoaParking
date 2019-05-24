#!/bin/bash

# Parameters check
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 DATASOURCE_USERNAME DATASOURCE_PASSWORD" >&2
    exit 1
fi

# Standalone.xml configuration
mv ../standalone/configuration/standalone.xml ../standalone/configuration/standalone-copy.xml
sed s/#{username}/$1/ ./standalone-form.xml | sed s/#{password}/$2/ > ../standalone/configuration/standalone.xml

# Driver setup
mkdir -p ../modules/com/mysql/main
cp ./module.xml ../modules/com/mysql/main/module.xml
cp ./mysql-connector-java-8.0.16.jar ../modules/com/mysql/main/
