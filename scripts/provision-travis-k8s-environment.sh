#!/bin/bash

echo "Provision Travis-CI for K8s"

export KUBE_CA_CERT=$(kubectl config view --flatten --output=json \
       | jq --raw-output '.clusters[0] .cluster ["certificate-authority-data"]')
export KUBE_ADMIN_CERT=$(kubectl config view --flatten --output=json \
       | jq --raw-output '.users[0] .user ["client-certificate-data"]')
export KUBE_ADMIN_KEY=$(kubectl config view --flatten --output=json \
       | jq --raw-output '.users[0] .user ["client-key-data"]')

travis env set KUBE_CA_CERT $KUBE_CA_CERT
travis env set KUBE_ADMIN_CERT $KUBE_ADMIN_CERT
travis env set KUBE_ADMIN_KEY $KUBE_ADMIN_KEY
