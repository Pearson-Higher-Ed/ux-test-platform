/**
 * Created by umahaea on 2/10/17.
 */
function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'inputs-target',
			componentName: 'TextInput',
			props: {
				id: 'a',
				label: 'FirstName',
				inputType: 'default',
				fancy: false,
				placeholder: 'FirstName'
			}
		}
	}));
}
window.onload = init;