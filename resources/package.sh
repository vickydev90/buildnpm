#!/usr/bin/env bash
set -ex
WORKSPACE=${WORKSPACE:-`pwd`}
artifact=$1




rm -rf j2
mkdir -p j2
set +e
tar -zcf j2/${artifact} .