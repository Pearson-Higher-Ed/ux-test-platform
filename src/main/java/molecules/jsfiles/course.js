document.addEventListener('DOMContentLoaded', function() {

  var element = document.querySelector('.demo-container');
  var config = {"mode":"Course","user":{"givenName":"John"},"courseNav":{"heading":{"text":"Physics","href":"https://example.com/physics"},"items":[{"text":"Performance","href":"https://example.com/performance","active":false},{"text":"Assessments","href":"https://example.com/assessments","active":false}]}};

  console.info(config);

  document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {
    detail: {
      element: element,
      config: config
    }
  }));
});