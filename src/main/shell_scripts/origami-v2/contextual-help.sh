#!/usr/bin/env bash

git clone https://github.com/Pearson-Higher-Ed/contextual-help.git
cd contextual-help
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/build/dist.contextual-help.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/
chmod 777 ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/dist.contextual-help.js
ls -ltr ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/