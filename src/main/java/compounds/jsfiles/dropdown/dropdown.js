var dropdown = React.createElement(Dropdown,
    {"mobileTitle":"mobile Title","type":"text","label":"text","id":"text"},
    React.createElement(DropdownItem, { label: 'list item 1', type:'link'}),
    React.createElement(DropdownItem, { type: 'divider'}),
    React.createElement(DropdownItem, { label: 'list item 2', type:'link'}),
    React.createElement(DropdownItem, { type: 'divider'}),);
ReactDOM.render(dropdown, document.getElementById('dropdown-target'));