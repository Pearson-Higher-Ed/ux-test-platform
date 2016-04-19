#!/usr/bin/env bash

git clone https://github.com/Pearson-Higher-Ed/elements.git
cd elements
node --version
npm --version
npm install --no-shrinkwrap
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/