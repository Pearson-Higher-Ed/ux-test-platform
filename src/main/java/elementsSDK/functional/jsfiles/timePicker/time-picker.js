function init() {
document.body.dispatchEvent(new CustomEvent('o.InitComponents', {detail:{elementId:'time-picker-target',componentName:'TimePicker',props:{inputState:'error',timeFormat:'hh:mm',labelText:'Select time'}}}));}window.onload = init;
