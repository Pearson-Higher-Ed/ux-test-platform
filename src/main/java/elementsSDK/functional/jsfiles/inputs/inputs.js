function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
        detail: {
            elementId: 'inputs-target',
            componentName: 'TextInput',
            props: {
                id: 'a',
                label: 'FirstName',
                inputType: 'default',
                placeholder: 'FirstName'
            }
        }
    }));
}
window.onload = init;
