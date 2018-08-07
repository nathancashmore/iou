# IOU
System to record how much you owe them.

# Prerequisite

* DB

# Run

```
DB_URL=jdbc:postgresql://localhost/iou DB_USER=postgres DB_PASSWORD=mysecretpassword DB_DRIVER=org.postgresql.Driver ./gradlew bootRun
```

# Test
Make sure you have an instance of the DB running with the following settings:

```
docker run --name iou-db \
    -p 5432:5432 \
    -e POSTGRES_DB=iou \
    -e POSTGRES_PASSWORD=mysecretpassword \
    -d postgres
```
If this has already been run you can start and stop the DB as follows:
```
docker start iou-db
docker stop iou-db
```

To restart with a fresh DB remove and recreate the docker instance
```
docker rm iou-db
```

To connect to the running DB use:
```
docker run -it --rm --link iou-db:postgres postgres psql -h postgres -U postgres iou
```

# Build


# Deployment
Let travis deploy the application to a pre provisioned environment.
Exececute the following once to initialise travis and docker with required deployment settings
```
./scripts/provision-travis-k8s-environment.sh
```

