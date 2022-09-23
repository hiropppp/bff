#!/bin/zsh

GIT_COMMIT_ID=$(git log --format="%H" -n 1)
aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin xxxxxxxxxxx.dkr.ecr.ap-northeast-1.amazonaws.com
docker build -t webapi:"${GIT_COMMIT_ID}" -f Dockerfile .
docker tag webapi:"${GIT_COMMIT_ID}" xxxxxxxxxxx.dkr.ecr.ap-northeast-1.amazonaws.com/webapi:"${GIT_COMMIT_ID}"
docker push xxxxxxxxxxx.dkr.ecr.ap-northeast-1.amazonaws.com/webapi:"${GIT_COMMIT_ID}"