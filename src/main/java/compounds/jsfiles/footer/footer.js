function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'footer-target',
			componentName: 'Footer',
			props: {
				copyrightText: 'Pearson Education Inc. All Rights Reserved',
				links:  [{ text: 'First link', href: 'first' }, { text: 'Second link', href: 'second' }, { text: 'Last link', href: 'last' }]
			}
		}
	}));
}
window.onload = init;
