/**
 * Created by umahaea on 2/10/17.
 */

function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'button-target',
			componentName: 'Button',
			props: {
				btnType: '',
				btnSize: 'large',
				children: 'children',
				onClick: (function () {
					return alert('Hello World!');
				})
			}
		}
	}));
}
window.onload = init;