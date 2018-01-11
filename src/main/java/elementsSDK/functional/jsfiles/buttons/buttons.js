/**
 * Created by umahaea on 2/10/17.
 */

function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'button-target',
			componentName: 'Button',
			props: {
btnType: 'primary',
btnSize: 'xlarge',
children: 'default-large',
				onClick: (function () {
					return alert('Hello World!');
				})
			}
		}
	}));
}
window.onload = init;
