#!/usr/bin/env bash
git clone https://github.com/Pearson-Higher-Ed/component-archetype.git
cd component-archetype
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/build/dist.component-name.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/componentArchetype/