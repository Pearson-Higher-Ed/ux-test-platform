function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
        detail: {
            elementId: 'button-target',
            componentName: 'Button',
            props: {
                btnType: 'tertiary',
                children: 'Tertiary Btn'
            }
        }
    }));
}
window.onload = init;