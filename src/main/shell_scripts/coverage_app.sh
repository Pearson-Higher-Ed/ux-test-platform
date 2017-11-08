#!/usr/bin/env bash
git clone https://github.com/umahaea/coverage-app.git
cd coverage-app
npm install &>/dev/null
node src/index.js &
cd ..