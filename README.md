# IOU
System to record how much you owe them..

# Test
```
./gradlew test

docker run --name iou-db \
    -p 5432:5432 \
    -e POSTGRES_DB=iou \
    -e POSTGRES_PASSWORD=mysecretpassword \
    -d postgres

docker run -it --rm --link iou-db:postgres postgres psql -h postgres -U postgres iou

docker stop iou-db

docker start iou-db

docker rm iou-db

```

# Deployment
Let travis deploy the application to a pre provisioned environment.
Exececute the following once to initialise travis and docker with required deployment settings
```
./scripts/provision-travis-k8s-environment.sh
```

