#!/bin/sh

if [ "x${JDG_HOME}" == "x" ]; then
    echo "Error: Please set environment variable JDG_HOME."
    exit
fi

bindir=$(cd $(dirname $0) && pwd)
cp=`ls ${JDG_HOME}/client/java/*.jar | tr '\n' ':'`

jrunscript -cp "$cp" -f "${bindir}/hotrodclient.js" -f -
