#!/bin/zsh

GIT_COMMIT_ID=$(git log --format="%H" -n 1)
aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin 263973347380.dkr.ecr.ap-northeast-1.amazonaws.com
docker build -t webapi:"${GIT_COMMIT_ID}" -f Dockerfile .
docker tag webapi:"${GIT_COMMIT_ID}" 263973347380.dkr.ecr.ap-northeast-1.amazonaws.com/webapi:"${GIT_COMMIT_ID}"
docker push 263973347380.dkr.ecr.ap-northeast-1.amazonaws.com/webapi:"${GIT_COMMIT_ID}"