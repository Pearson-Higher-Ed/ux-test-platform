/**
 * Created by umahaea on 6/10/16.
 */
function init() {
	document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
			detail: {
				elementId: 'drawer-target',
				props: {
					text: {
						headerTitle: 'Basic Title',
						closeButtonSRText: 'Close',
						backButtonText: 'Back'
					},
					drawerOpen: true,
					position: 'center',
					drawerTop: '61px',
					drawerHandler: function () {
					},
					children: React.createElement('div', {},
						React.createElement(BasicView, {mapToDetail: 'detailView1', myKind: 'BasicView'},
							React.createElement('p', {}, 'hi')
						),
						React.createElement(BasicView, {mapToDetail: 'detailView1', myKind: 'BasicView'},
							React.createElement('p', {}, 'hello')
						),
						React.createElement(DetailView, {id: 'detailView1', myKind: 'DetailView'},
							React.createElement('p', {}, 'there')
						)
					)
				}
			}
		}
	));
}
window.onload = init;