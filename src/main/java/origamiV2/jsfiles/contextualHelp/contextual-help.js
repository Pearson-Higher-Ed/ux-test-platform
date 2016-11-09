/**
 * Created by umahaea on 4/27/16.
 */

document.addEventListener("DOMContentLoaded", function () {
  document.dispatchEvent(new CustomEvent('o.InitContextualHelp'));
  var help = document.getElementById('o-contextual-help-drawer').oContextualHelp;
  var newTopics = ["testcontent/student/deletedcourse", "testcontent/student/droppedcourse", "testcontent/student/freetrial"];
  help.addTopics(newTopics);
});

document.addEventListener('oDrawer.open', function (e) {
  document.getElementById("demo").innerHTML = "Drawer is opened";
});

document.addEventListener('oDrawer.close', function (e) {
  document.getElementById("demo").innerHTML = "Drawer is closed";
});

document.openSpecificArticle = function (topic) {
  document.dispatchEvent(new CustomEvent('o.InitContextualHelp'));
  var help = document.getElementById('o-contextual-help-drawer').oContextualHelp;
  help.openHelpTopic(topic);
}

document.openThenRemoveAllTopics = function(){
  document.dispatchEvent(new CustomEvent('o.InitContextualHelp'));
  var help=document.getElementById('o-contextual-help-drawer').oContextualHelp;
  help.open();
  setTimeout(function(){
    help.removeAllTopics()
  }, 500);
}

document.openThenRemoveTopics = function(topics, delay){
  var help = document.getElementById('o-contextual-help-drawer').oContextualHelp;
  help.open();
  setTimeout(function(){
    help.removeTopics(topics)
  }, delay);
}

document.setLanguageThenOpen = function(){
	var help = document.getElementById('o-contextual-help-drawer').oContextualHelp;
	help.removeAllTopics();
	help.setLanguage('fr');
	var newTopics = [
		'pi/forgot_creds_next',
		'contactsupport'
	];
	help.addTopics(newTopics);
	help.open();
}

var testAccordianContent = {
  title: 'Test Accordion',
  excerpt: 'A test of the accordion for content.',
  content: '<div class="o-contextual-help__accordion"> <h3><button class="o-disclosure" aria-controls="item-1" aria-expanded="false"><i class="pe-icon--caret-right" aria-hidden="true"></i> Item 1</button></h3> <div id="item-1" class="o-panel--closed"> <p >Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p> </div> <h3><button class="o-disclosure" aria-controls="item-2" aria-expanded="false"><i class="pe-icon--caret-right" aria-hidden="true"></i> Item 2</button></h3> <div id="item-2" class="o-panel--closed"> <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p> </div> <h3><button class="o-disclosure" aria-controls="item-3" aria-expanded="false"><i class="pe-icon--caret-right" aria-hidden="true"></i> Item 3</button></h3> <div id="item-3" class="o-panel--closed"> <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p> </div> <h3><button class="o-disclosure" aria-controls="item-4" aria-expanded="false"><i class="pe-icon--caret-right" aria-hidden="true"></i> Item 4</button></h3> <div id="item-4" class="o-panel--closed"> <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p> </div> </div>'
};

document.testAccordion = function(){
  var help = document.getElementById('o-contextual-help-drawer').oContextualHelp;
  help.cache.accordion = testAccordianContent;
  help.openHelpTopic('accordion');
}