/**
 * Created by umahaea on 6/1/17.
 */
function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitModal', {
		detail: {
			elementId: 'modal-target',
			props: {
				text: {
					headerTitle: 'Terms n Conditions (basic title)',
					closeButtonSRText: 'close',
					modalSaveButtonText: 'save',
					modalCancelButtonText: 'cancel'
				},
				isShown: true,
				cancelBtnHandler: function () {
					console.log('You clicked Cancel!');
				},
				successBtnHandler: function () {
					console.log('You clicked save!');
				},
				footerVisible: true,
				children: React.createElement('p', {}, 'Lorem ipsum dolor sit amet')
			}
		}
	}));
}
window.onload = init;
