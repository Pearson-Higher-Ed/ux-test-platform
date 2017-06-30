function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
        detail: {
            elementId: 'dropdown-target',
            componentName: 'Dropdown',
            props: {
                presentationType: 'button',
                presentationText: 'button',
                list: ['Thing one', 'Thing two'],
                mobileTitle: 'Mobile title',
                dropdownControlLabel: 'This is a button dropdown'
            }
        }
    }));
}
window.onload = init;