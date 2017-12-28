# IOU
System to record how much you owe them..

# Test
```
./gradlew test
```

# Deployment
Let travis deploy the application to a pre provisioned environment.
Excecute the following once to initialise travis and docker with required deployment settings
```
./scripts/provision-travis-k8s-environment.sh
```