document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
        detail: {
            elementId: 'drawer',
            props: {
                text: {
                    headerTitle       : "Basic Title",
                    closeButtonSRText : "Close",
                    backButtonText    : "Back"
                },
                drawerOpen: true,
                position:"right",
                drawerTop:"61px",
                drawerHandler: () => {},
                children: React.createElement('div', {},
                    React.createElement(BasicView,{mapToDetail:'detailView1',myKind:'BasicView'},
                        React.createElement('p',{},'hi')
                    ),
                    React.createElement(DetailView,{id:'detailView1',myKind:'DetailView'},
                        React.createElement('p',{},'there')
                    )
                )
            }
        }
    }
));