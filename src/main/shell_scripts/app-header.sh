#!/usr/bin/env bash

git clone https://github.com/Pearson-Higher-Ed/app-header.git
cd app-header
#git checkout elements
node --version
npm --version
npm install --no-shrinkwrap
npm run build
#npm run dev-setup
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/fixtures/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/build/dist.app-header.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/jsfiles/
ls -ltr ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/molecules/fixtures/
ls -ltr ~/build/Pearson-Higher-Ed/ux-test-platform/fonts/