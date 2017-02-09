/**
 * Created by umahaea on 2/15/17.
 */

function init() {
  document.body.dispatchEvent(new CustomEvent('o.InitCompounds', {
    detail: {
      elementId: 'button-target',
      componentName: 'Button',
      props: {
        btnType: '',
        btnSize: 'large',
        children: 'children',
        onClick: (function () {
          return alert('Hello World!');
        })
      }
    }
  }));
}
window.onload = init;
