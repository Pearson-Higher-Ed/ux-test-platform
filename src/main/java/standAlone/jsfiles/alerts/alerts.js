
function init() {
  document.body.dispatchEvent(new CustomEvent('o.InitAlertsComponent', {
    detail: {
      elementId: 'alert-target',
      locale: 'en'
    }

  }));
 }
window.onload = init;