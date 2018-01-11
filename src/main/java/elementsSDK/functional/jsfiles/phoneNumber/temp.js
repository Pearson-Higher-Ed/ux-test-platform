function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'phone-number-target',
			componentName: 'PhoneNumber',
			props: {
				labelText: 'Mobile Phone',
				placeholder: '555-555-5555',
				fancy: true,
				infoMessage: 'Test Info'
			}
		}
	}));
}
window.onload = init;
