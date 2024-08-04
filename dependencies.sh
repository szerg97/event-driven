#!/bin/bash
$1

function startAP() {
  cd docker && docker compose up ap-db zookeeper broker schema-registry
}

function startPS() {
  cd docker && docker compose up pub-db ap-db bp-db zookeeper broker schema-registry
}

function stopAP() {
  cd docker && docker compose down ap-db zookeeper broker schema-registry
}

function stopPS() {
  cd docker && docker compose down pub-db ap-db bp-db zookeeper broker schema-registry
}

if [ "$1" == "up" ]; then
    echo "Starting service..."
    #startAP
    startPS
elif [ "$1" == "down" ]; then
    echo "Stopping service..."
    #stopAP
    stopPS
else
    echo "Invalid argument. Please use 'up' or 'down'."
    exit 1
fi
