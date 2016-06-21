#!/usr/bin/env bash

git clone https://github.com/Pearson-Higher-Ed/drawer.git
cd drawer
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/drawer/build/dist.drawer.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/drawer/