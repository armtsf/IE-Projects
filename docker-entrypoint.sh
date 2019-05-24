#!/bin/sh
while ! nc -z db 3306; do sleep 2; done
sleep 2
java -jar joboonja.jar