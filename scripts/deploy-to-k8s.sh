#!/bin/bash

kubectl set image deployment/$APP $APP=$REPO:$COMMIT;
kubectl rollout history deployment/$APP;
