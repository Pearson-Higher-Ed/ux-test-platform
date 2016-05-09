/**
 * Created by umahaea on 3/24/16.
 */

document.addEventListener('DOMContentLoaded', function() {

  var element = document.querySelector('.demo-container');
  var config = {"mode":"Signed Out","showLoginControls": true};
  console.info(config);
  document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {
    detail: {
      element: element,
      config: config
    }
  }));
});