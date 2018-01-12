function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
        detail:{
            elementId:'spinner-target',
            componentName:'LoadingSpinner',
            props:{
            }
        }
    }));
}
window.onload = init;