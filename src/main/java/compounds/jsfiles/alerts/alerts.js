function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
            detail: {
                elementId: 'StaticAlert',
                componentName: 'StaticAlert',
                props: {
                    type: 'Error',
                    title: 'Inline title',
                    message: 'Hello this is an informative msg',
                    inline: false,
                    disable: false
                }
            }
        }
    ));
}
window.onload = init;
