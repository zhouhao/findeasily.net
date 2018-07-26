#!/usr/bin/env bash

set -e

SSH_USER=root
SSH_HOST=findeasily.net
mvn clean package

scp -i script/findeasily_rsa target/website.jar $SSH_USER@$SSH_HOST:/root/
ssh -i script/findeasily_rsa $SSH_USER@$SSH_HOST "systemctl restart website.service"

echo "The website will be ready in about 1 minute!"