#!/bin/bash

curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

mkdir ${HOME}/.kube
cp kube/kubeconfig ${HOME}/.kube/config

kubectl config set users.iou.staticvoid.co.uk.client-key-data $KUBE_ADMIN_KEY
kubectl config set users.iou.staticvoid.co.uk.client-certificate-data $KUBE_ADMIN_CERT
kubectl config set clusters.iou.staticvoid.co.uk.certificate-authority-data $KUBE_CA_CERT

kubectl get nodes
