#!/bin/bash
set -e

BLACK_ON_GREY=$'\e[47;30;1m'
NONE=$'\e[0m'

echo "${BLACK_ON_GREY}Provision Travis-CI for K8s${NONE}"

export KUBE_CA_CERT=$(kubectl config view --flatten --output=json \
       | jq --raw-output '.clusters[0] .cluster ["certificate-authority-data"]')
export KUBE_ADMIN_CERT=$(kubectl config view --flatten --output=json \
       | jq --raw-output '.users[0] .user ["client-certificate-data"]')
export KUBE_ADMIN_KEY=$(kubectl config view --flatten --output=json \
       | jq --raw-output '.users[0] .user ["client-key-data"]')

travis env set KUBE_CA_CERT $KUBE_CA_CERT
travis env set KUBE_ADMIN_CERT $KUBE_ADMIN_CERT
travis env set KUBE_ADMIN_KEY $KUBE_ADMIN_KEY

echo "${BLACK_ON_GREY}Provision Docker${NONE}"

read -p 'Enter Docker username: ' dockerUsername
read -sp 'Enter Docker password: ' dockerPassword

docker login -u $dockerUsername -p $dockerPassword

travis env set DOCKER_USERNAME $dockerUsername
travis env set DOCKER_PASSWORD $dockerPassword

travis restart
