#!/usr/bin/env bash
function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}


set -e

./gradlew build -x test

note "Copying artifact file of product service ...."
cp ./business/product-service/build/libs/product-service-0.0.1-SNAPSHOT.jar ./deployment/
note "DONE...."

note "Copying artifact file of cart service ...."
cp ./business/cart-service/build/libs/cart-service-0.0.1-SNAPSHOT.jar ./deployment/
note "DONE...."

note "Copying artifact file of gateway service ...."
cp ./supporters/api-gateway/build/libs/api-gateway-0.0.1-SNAPSHOT.jar ./deployment/
note "DONE...."

note "building docker images...."
docker-compose build
note "DONE...."
