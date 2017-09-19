function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'calendar-target',
			componentName: 'Calendar',
			props: {
				disablePast: true,
				contrast: false
			}
		}
	}));
}
window.onload = init;
