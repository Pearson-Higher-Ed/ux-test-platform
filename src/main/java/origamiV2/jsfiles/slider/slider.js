function init() {

  document.body.dispatchEvent(new CustomEvent('o.InitMySliderComponent', {
    detail: {
      elementId: 'slider-target'
    }
  }));
}

window.onload = init;