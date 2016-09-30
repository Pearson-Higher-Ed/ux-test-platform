#!/usr/bin/env bash

echo "component: $component"
echo "feature_branch: $feature_branch"
grn=$'\e[1;32m'

install_elements_sdk() {
echo -e "******************************\\n    Installing elements sdk V1   \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/elements.git &>/dev/null
cd elements
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/css/elementsNoPlain.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
printf "${grn}EndOf elements_sdk Installation\n"
}

install_compounds_sdk() {
echo -e "******************************\\n    Installing compounds sdk V0   \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/compounds.git &>/dev/null
cd compounds
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/build/dist.compounds.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/jsfiles/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/build/eventInstantiator.compounds.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/jsfiles/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/css/
printf "${grn}EndOf compounds_sdk Installation\n"
}

install_appHeader(){
echo -e "******************************\\n    Installing app-header V2  \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/app-header.git &>/dev/null
cd app-header
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/build/dist.app-header.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/appHeader/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/appHeader/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/images ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/
printf "${grn}EndOf app-header Installation\n"
}

install_contextualHelp(){
echo -e "******************************\\n    Installing contextual-help V2   \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/contextual-help.git &>/dev/null
cd contextual-help
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/build/dist.contextual-help.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/contextualHelp/
printf "${grn}EndOf contextual-help Installation\n"
}

install_drawer(){
echo -e "******************************\\n    Installing drawer    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/drawer.git &>/dev/null
cd drawer
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/drawer/build/dist.drawer.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/drawer/
printf "${grn}EndOf drawer Installation\n"
}

install_componentArchetype(){
echo -e "******************************\\n    Installing component-archetype    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/component-archetype.git &>/dev/null
cd component-archetype
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/build/dist.component-name.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/componentArchetype/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/componentArchetype/
printf "${grn}EndOf component-archetype Installation\n"
}

install_avatarDisplay(){
echo -e "******************************\\n    Installing avatar-display    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/avatar-display.git &>/dev/null
cd avatar-display
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/build/dist.avatar-display.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/avatarDisplay/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/avatarDisplay/
printf "${grn}EndOf avatar-display Installation\n"
}

install_slider(){
echo -e "******************************\\n    Installing slider    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/slider.git &>/dev/null
cd slider
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/slider/build/dist.slider.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/slider/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/slider/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/slider/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/slider/slider.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/slider/
printf "${grn}EndOf slider Installation\n"
}

install_textModal(){
echo -e "******************************\\n    Installing text-modal    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/text-modal.git &>/dev/null
cd text-modal
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/text-modal/build/dist.text-modal.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/textModal/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/text-modal/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/text-modal/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/textModal/
printf "${grn}EndOf text-modal Installation\n"
}

install_alerts(){
echo -e "******************************\\n    Installing alerts    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/alerts.git &>/dev/null
cd alerts
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/build/dist.alerts.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/alerts/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/alerts/
printf "${grn}EndOf alerts Installation\n"
}

install_pagination(){
echo -e "******************************\\n    Installing pagination    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/pagination.git &>/dev/null
cd pagination
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/pagination/build/dist.pagination.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/pagination/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/pagination/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/pagination/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/pagination/
printf "${grn}EndOf pagination Installation\n"
}

install_modal(){
echo -e "******************************\\n    Installing modal    \\n******************************"
cd ~/build/Pearson-Higher-Ed/ux-test-platform/
git clone https://github.com/Pearson-Higher-Ed/modal.git &>/dev/null
cd modal
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/modal/build/dist.modal.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/modal/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/modal/build/dev.modal.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/modal/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/modal/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/modal/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/modal/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/modal/
printf "${grn}EndOf modal Installation\n"
}

# Below conditions are to install the components specific to its feature branch.
if [[ $component == "elements_sdk" ]]
then
install_elements_sdk $feature_branch

elif [[ $component == "compounds_sdk" ]]
then
install_compounds_sdk $feature_branch

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

elif [[ $component == "slider" ]]
then
install_slider $feature_branch

elif [[ $component == "text-modal" ]]
then
install_textModal $feature_branch

elif [[ $component == "alerts" ]]
then
install_alerts $feature_branch

elif [[ $component == "pagination" ]]
then
install_pagination $feature_branch

elif [[ $component == "modal" ]]
then
install_modal $feature_branch

# Below condition is to install all the "master" branch of components for the regression test run
elif [[ $component == "regression" ]]
then
install_appHeader master &
install_contextualHelp master &
install_drawer master &
install_avatarDisplay master &
install_slider master &
install_textModal master &
install_alerts master &
install_pagination master &
install_modal master &
install_elements_sdk v1 &
install_compounds_sdk v0 &
fi
jobs -l
wait