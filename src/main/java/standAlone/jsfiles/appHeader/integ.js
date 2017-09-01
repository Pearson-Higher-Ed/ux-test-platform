/**
 * Created by umahaea on 4/6/16.
 */
document.addEventListener('DOMContentLoaded', function() {

  var element = document.querySelector('.demo-container');
  var config = {"mode": "Integration"};

  console.info(config);

  document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {
    detail: {
      element: element,
      config: config
    }
  }));
});