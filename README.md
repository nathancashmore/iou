# IOU
System to record how much you owe them.

# Prerequisite

* DB

# Run

```
DB_URL=jdbc:postgresql://localhost/iou DB_USER=postgres DB_PASSWORD=password DB_DRIVER=org.postgresql.Driver ./gradlew bootRun
```

# Test
Make sure you have an instance of the DB running with the following settings:

```
docker run --name iou-db \
    --publish 5432:5432 \
    --env POSTGRES_DB=iou \
    --env POSTGRES_PASSWORD=password \
    --detach 
    postgres
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

To connect to a remote DB use:
```
psql "sslmode=disable dbname=iou user=postgres hostaddr=[IP_ADDRESS]"
```

# Docker deployment
## Build
See the travis.yml for details but essentially this boils down to the following:
```
./gradlew clean build
docker build -t iou . --build-arg JAR_FILE=./build/libs/iou-0.0.1-SNAPSHOT.jar
```

## Run local app with local db 
```
# Run locally against postgres iou-db
docker run --name iou-instance \
    --publish 8080:8080 \
    --env-file ./env/local_env \
    --detach --rm \
    --link iou-db:postgres iou:latest \
    iou

```

## Run local app with remote DB
Add environment details to appropriate file e.g. ```./env/dev_env``` 
```
DB_URL=jdbc:postgresql://[REMOTE_DB_URL]:5432/iou
DB_USER=postgres
DB_PASSWORD=[YOUR_DB_PASSWORD]
DB_DRIVER=org.postgresql.Driver

```
Start instance and specify environment
```
docker run --detach --rm --env-file ./env/dev_env --publish 8080:8080 --name iou-instance iou
```

# Deployment
Let travis deploy the application to a pre provisioned environment.
Exececute the following once to initialise travis and docker with required deployment settings
```
./scripts/provision-travis-k8s-environment.sh
```

