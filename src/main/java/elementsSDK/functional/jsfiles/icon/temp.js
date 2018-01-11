/**
 * Created by umahaea on 2/10/17.
 */
function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'icon-target',
			componentName: 'Icon',
			props: {
name: 'font-setting-18'
			}
		}
	}));
}
window.onload = init;
