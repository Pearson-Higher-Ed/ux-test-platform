/**
 * Created by umahaea on 6/9/16.
 */

function init() {

  // Demo eventing API
  document.body.dispatchEvent(new CustomEvent('o.InitMyComponent', {
    detail: {
      elementId: 'demo-target1',
      greeting: 'Hello world!'
    }
  }));
  document.body.dispatchEvent(new CustomEvent('o.InitMyComponent', {
    detail: {
      elementId: 'demo-target2',
      greeting: 'Bonjour le monde!',
      locale: 'fr'
    }
  }));

  // Demo direct API
  /*  new MyComponent({
   elementId: 'demo-target2',
   greeting: 'Bonjour le monde!',
   locale: 'fr'
   });*/
}
window.onload = init;
