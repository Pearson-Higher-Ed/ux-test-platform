function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'calendar-target',
			componentName: 'Calendar',
			props: {
				disablePast: true,
				contrast: true
			}
		}
	}));
}
window.onload = init;
