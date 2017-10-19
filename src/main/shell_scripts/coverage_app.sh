#!/usr/bin/env bash
pwd
npm install
git clone https://github.com/umahaea/coverage-app.git
cd coverage-app
npm install
pwd
node src/index.js &
cd ..