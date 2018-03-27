function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitModal', {
        detail: {
            elementId: 'drawer',
            props: {
                text: {headerTitle: 'Basic Title', closeButtonSRText: 'Close', backButtonText: 'Back'},
                drawerOpen: true,
                position: 'right',
                drawerTop: '61px',
                drawerHandler: function () {
                    console.log('Hello');
                },
                children: React.createElement('div', {}, React.createElement(BasicView, {
                    mapToDetail: 'detailView1',
                    myKind: 'BasicView'
                }, React.createElement('p', {}, 'hi')), React.createElement(DetailView, {
                    id: 'detailView1',
                    myKind: 'BasicView'
                }, React.createElement('p', {}, 'there')))
            }
        }
    }));
}
window.onload = init;