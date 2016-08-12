#!/usr/bin/env bash

echo "component: $component"
echo "feature_branch: $feature_branch"

install_elements_sdk() {
git clone https://github.com/Pearson-Higher-Ed/elements.git
cd elements
git checkout $1
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
}

install_appHeader(){
git clone https://github.com/Pearson-Higher-Ed/app-header.git
cd app-header
git checkout $1
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/build/dist.app-header.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/appHeader/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
}

install_contextualHelp(){
git clone https://github.com/Pearson-Higher-Ed/contextual-help.git
cd contextual-help
git checkout $1
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/build/dist.contextual-help.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
}

install_drawer(){
git clone https://github.com/Pearson-Higher-Ed/drawer.git
cd drawer
git checkout $1
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/drawer/build/dist.drawer.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/drawer/
}

install_componentArchetype(){
git clone https://github.com/Pearson-Higher-Ed/component-archetype.git
cd component-archetype
git checkout $1
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/build/dist.component-name.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/reactComponents/jsfiles/componentArchetype/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
}

install_avatarDisplay(){
git clone https://github.com/Pearson-Higher-Ed/avatar-display.git
cd avatar-display
git checkout $1
npm install
npm run build
cp ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/build/dist.avatar-display.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/reactComponents/jsfiles/avatarDisplay/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
}

# Below conditions are to install the components specific to its feature branch.
if [[ $component == "elements_sdk" ]]
then
install_elements_sdk $feature_branch

elif [[ $component == "app-header" ]]
then
install_appHeader $feature_branch

elif [[ $component == "contextual-help" ]]
then
install_appHeader master
cd ..
install_contextualHelp $feature_branch

elif [[ $component == "drawer" ]]
then
install_drawer $feature_branch

elif [[ $component == "component-archetype" ]]
then
install_componentArchetype $feature_branch

elif [[ $component == "avatar-display" ]]
then
install_avatarDisplay $feature_branch

# Below condition is to install all the "master" branch of components for the regression test run
elif [[ $component == "regression" ]]
then
install_appHeader master
cd ..
install_contextualHelp master
cd ..
install_drawer master
cd ..
install_componentArchetype master
cd ..
install_avatarDisplay master
cd ..
install_elements_sdk v0
fi
