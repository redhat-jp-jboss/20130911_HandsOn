@echo off
set bindir=%~dp0

if "%JDG_HOME"=="" (
    echo Error: Please set environment variable JDG_HOME.
    goto end
)

set cp=%JDG_HOME%\client\java\commons-pool-1.6-redhat-2.jar
set cp=%cp%;%JDG_HOME%\client\java\infinispan-client-hotrod-5.2.4.Final-redhat-1.jar
set cp=%cp%;%JDG_HOME%\client\java\infinispan-core-5.2.4.Final-redhat-1.jar
set cp=%cp%;%JDG_HOME%\client\java\jboss-logging-3.1.2.GA-redhat-1.jar
set cp=%cp%;%JDG_HOME%\client\java\jboss-marshalling-1.3.15.GA-redhat-1.jar
set cp=%cp%;%JDG_HOME%\client\java\jboss-marshalling-river-1.3.15.GA-redhat-1.jar

jrunscript -cp "%cp%" -f "%bindir%hotrodclient.js" -f -

:end
