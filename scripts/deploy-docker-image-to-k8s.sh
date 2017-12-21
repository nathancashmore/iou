#!/bin/bash

docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD";
docker tag $REPO:$COMMIT $REPO:latest;
docker push $REPO;

kubectl set image deployment/$APP $APP=$REPO:$COMMIT;
kubectl rollout history deployment/$APP;
