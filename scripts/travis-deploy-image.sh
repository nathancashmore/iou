#!/bin/bash

docker login -u="$DOCKER_USERNAME" -p="$DOCKER_PASSWORD";
docker tag $REPO:$COMMIT $REPO:latest;
docker push $REPO;

kubectl --kubeconfig kube/kubeconfig set image deployment/$APP $APP=$REPO:$COMMIT;
kubectl --kubeconfig kube/kubeconfig rollout history deployment/$APP;
