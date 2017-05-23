function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponents', {
		detail: {
			elementId: 'modal-target',
			componentName: 'Modal',
			props: {
				text: {
					initiatingButtonText: 'any string',
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
