function init() {
    var tableArray = [React.createElement(TableHead, {},
        React.createElement(TableRow, {},
            React.createElement(TableHeaderCell, {children: 'Header 1'}),
            React.createElement(TableHeaderCell, {children: 'Header 2'}))),
        React.createElement(TableBody, {},
            React.createElement(TableRow, {},
                React.createElement(TableRowCell, {children: 'Child 1'}),
                React.createElement(TableRowCell, {children: 'Child 2'})))]
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
        detail: {
            elementId: 'tables-target',
            componentName: 'Table',
            props: {selectable: false, sortable: false, children: React.Children.toArray(tableArray)}
        }
    }));
}
