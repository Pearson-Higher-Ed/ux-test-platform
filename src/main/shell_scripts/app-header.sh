#!/usr/bin/env bash

git clone https://github.com/Pearson-Higher-Ed/app-header.git
cd app-header
node --version
npm --version
npm install --no-shrinkwrap
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/build/dist.app-header.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/