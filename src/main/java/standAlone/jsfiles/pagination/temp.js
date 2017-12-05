function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitPagination', {
        detail: {
            elementId: 'pagination-target',
            props: {activePage: 1, pages: 10}
        }
    }));
}
window.onload = init;
