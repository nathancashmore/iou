#!/bin/bash

curl -Lo kubectl https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl && chmod +x kubectl && sudo mv kubectl /usr/local/bin/

sed -i -e 's|KUBE_CA_CERT|'"${KUBE_CA_CERT}"'|g' ./kube/kubeconfig
sed -i -e 's|KUBE_ENDPOINT|'"${KUBE_ENDPOINT}"'|g' ./kube/kubeconfig
sed -i -e 's|KUBE_ADMIN_CERT|'"${KUBE_ADMIN_CERT}"'|g' ./kube/kubeconfig
sed -i -e 's|KUBE_ADMIN_KEY|'"${KUBE_ADMIN_KEY}"'|g' ./kube/kubeconfig
sed -i -e 's|KUBE_USERNAME|'"${KUBE_USERNAME}"'|g' ./kube/kubeconfig

