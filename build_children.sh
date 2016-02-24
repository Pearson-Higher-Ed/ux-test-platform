#!/bin/bash

# Get last child project build number
BUILD_NUM=$(curl -s 'https://api.travis-ci.org/repos/Pearson-Higher-Ed/travis-test/builds' | grep -o '^\[{"id":[0-9]*,' | grep -o '[0-9]' | tr -d '\n')
echo $BUILD_NUM
# Restart last child project build
curl -X POST https://api.travis-ci.org/builds/$BUILD_NUM/restart "Authorization: token "$AUTH_TOKEN
