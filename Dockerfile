FROM ubuntu:latest
LABEL authors="anant.borse"

ENTRYPOINT ["top", "-b"]