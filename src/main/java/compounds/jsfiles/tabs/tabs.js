function init() {
	var paneArray = [React.createElement('Pane', {label: 'Tab 1'}, React.createElement('div', {}, 'Tab 1 Menu')), React.createElement('Pane', {label: 'Tab 2'}, React.createElement('div', {}, 'Tab 2 Menu'))]
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'tabs-target',
			componentName: 'Tabs',
			props: {
				light: false,
				children: React.Children.toArray(paneArray)
			}
		}
	}));
}
window.onload = init;
