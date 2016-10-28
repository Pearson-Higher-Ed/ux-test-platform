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
