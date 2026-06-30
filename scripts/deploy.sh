#!/bin/bash

set -e

echo "Starting CloudDeploy deployment..."

cd ~/cloud-deploy

echo "Pulling latest code..."
git pull

echo "Stopping existing containers..."
docker compose down

echo "Building updated containers..."
docker compose up --build -d

echo "Cleaning unused Docker images..."
docker image prune -f

echo "Deployment completed successfully!"