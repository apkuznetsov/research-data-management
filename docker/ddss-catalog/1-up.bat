@ECHO OFF
docker-compose up
docker network create -d bridge my-network
pause