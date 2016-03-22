/**
 * Created by umahaea on 3/22/16.
 */
document.addEventListener('DOMContentLoaded', function () {

  var element = document.querySelector('.demo-container');
  var config = {
    "mode": "Course",
    "user": {
      "givenName": "John"
    },
    "courseNav": {
      "heading": {"text": "Physics", "href": "https://example.com/physics"},
      "items": [
        {"text": "Performance", "href": "https://example.com/performance"},
        {"text": "Assessments", "href": "https://example.com/assessments", "active": false},
        {"text": "Score", "href": "https://example.com/assessments", "active": false}
      ]
    }
  };

  console.info(config);

  document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {
    detail: {
      element: element,
      config: config
    }
  }));

  // Event Listeners

  // Help menu
  document.addEventListener('oAppHeader.help.toggle', function () {
    alert('You toggled help');
    console.log('oAppHeader.help.toggle');
  });

  // Sign out event
  document.addEventListener('oAppHeader.logout', function () {
    console.log('oAppHeader.logout');
  });

});