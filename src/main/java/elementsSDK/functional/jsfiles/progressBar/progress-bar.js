function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'progress-bar-target',
			componentName: 'ProgressBar',
			props: {
				min: 0,
				max: 100,
				value: 40,
				type: 'animated',
				alignLabel: 'center',
				labelText: '% completed',
				id: 'test-id',
                valueText : 'HelloWorld'
			}
		}
	}));
}
window.onload = init;
