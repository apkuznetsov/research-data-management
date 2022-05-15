@ECHO OFF
docker network create --driver bridge my-net
docker-compose up
pause