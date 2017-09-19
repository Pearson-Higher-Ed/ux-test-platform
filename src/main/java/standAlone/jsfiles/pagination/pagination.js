function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitPagination', {
        detail: {
            elementId: 'pagination',
            locale: 'en',
            activePage: 1,
            items: 10,
            maxButtons: 5
        }
    }));
}
window.onload = init;
