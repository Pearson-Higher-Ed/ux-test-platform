#!/usr/bin/env bash

echo "component: $component"
echo "feature_branch: $feature_branch"

install_elements_sdk() {
echo -e "******************************\\n    Installing elements sdk V1   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/elements.git
cd elements
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/css/elementsNoPlain.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/elements/css/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
}

install_compounds_sdk() {
echo -e "******************************\\n    Installing compounds sdk V0   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/compounds.git
cd compounds
git checkout $1
npm install &>/dev/null
#npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/build/dist.compounds.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/jsfiles/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/build/eventInstantiator.compounds.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/jsfiles/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/compounds/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/compounds/css/
}

install_appHeader(){
echo -e "******************************\\n    Installing app-header V2  \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/app-header.git
cd app-header
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/build/dist.app-header.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/appHeader/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/appHeader/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/app-header/images ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/
}

install_contextualHelp(){
echo -e "******************************\\n    Installing contextual-help V2   \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/contextual-help.git
cd contextual-help
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/build/dist.contextual-help.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/contextualHelp/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/contextual-help/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/contextualHelp/
}

install_drawer(){
echo -e "******************************\\n    Installing drawer    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/drawer.git
cd drawer
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/drawer/build/dist.drawer.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/drawer/
}

install_componentArchetype(){
echo -e "******************************\\n    Installing component-archetype    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/component-archetype.git
cd component-archetype
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/build/dist.component-name.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/componentArchetype/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/component-archetype/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/componentArchetype/
}

install_avatarDisplay(){
echo -e "******************************\\n    Installing avatar-display    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/avatar-display.git
cd avatar-display
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/build/dist.avatar-display.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/avatarDisplay/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/avatar-display/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/avatarDisplay/
}

install_slider(){
echo -e "******************************\\n    Installing slider    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/slider.git
cd slider
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/slider/build/dist.slider.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/slider/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/slider/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/slider/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/slider/slider.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/slider/
}

install_textModal(){
echo -e "******************************\\n    Installing text-modal    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/text-modal.git
cd text-modal
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/text-modal/build/dist.text-modal.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/textModal/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/text-modal/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/text-modal/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/textModal/
}

install_alerts(){
echo -e "******************************\\n    Installing alerts    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/alerts.git
cd alerts
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/build/dist.alerts.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/alerts/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/alerts/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/alerts/
}

install_pagination(){
echo -e "******************************\\n    Installing pagination    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/pagination.git
cd pagination
git checkout $1
npm install &>/dev/null
npm run copy-utils
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/pagination/build/dist.pagination.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/pagination/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/pagination/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/pagination/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/pagination/
}

install_modal(){
echo -e "******************************\\n    Installing modal    \\n******************************"
git clone https://github.com/Pearson-Higher-Ed/modal.git
cd modal
git checkout $1
npm install &>/dev/null
npm run build &>/dev/null
cp ~/build/Pearson-Higher-Ed/ux-test-platform/modal/build/dist.modal.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/modal/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/modal/build/dev.modal.js ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/jsfiles/modal/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/modal/node_modules/pearson-elements/dist/fonts ~/build/Pearson-Higher-Ed/ux-test-platform/
cp -R ~/build/Pearson-Higher-Ed/ux-test-platform/modal/node_modules/pearson-elements/dist/icons ~/build/Pearson-Higher-Ed/ux-test-platform/
cp ~/build/Pearson-Higher-Ed/ux-test-platform/modal/node_modules/pearson-elements/dist/css/elements.css ~/build/Pearson-Higher-Ed/ux-test-platform/src/main/java/origamiV2/css/modal/
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

# Below condition is to install all the "master" branch of components for the regression test run, regression split into 3 suites
elif [[ $component == "regression" ]]
then
echo $TEST_SUITE
if [[ $TEST_SUITE == "stand_alone" ]]
then
install_appHeader master &
install_contextualHelp master &
install_drawer master &
install_avatarDisplay master &
install_slider master &
#install_textModal master &
install_alerts master &
install_pagination master &
install_modal master &
fi
if [[ $TEST_SUITE == "elements_sdk" ]]
then
install_elements_sdk v1 &
fi
if [[ $TEST_SUITE == "compounds_sdk" ]]
then
install_compounds_sdk v0 &
fi
jobs -l
wait
fi
jobs -l