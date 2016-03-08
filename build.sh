#!/bin/bash
mvn clean compile assembly:single
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Build succeded"
else
echo "Build failed"
fi