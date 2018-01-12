function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'date-picker-target',
			componentName: 'DatePicker',
			props: {
				inputState: 'default',
				dateFormat: 'mm/dd/yyyy',
				labelText: 'Select date',
				disablePast: true
			}
		}
	}));
}
window.onload = init;