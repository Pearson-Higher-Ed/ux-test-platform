function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
        detail: {
            elementId: 'contextual-help-target',
            props: {
                directTopic: undefined,
                drawerTop: "61px",
                handleHelp: function () {
                },
                language: 'fr',
                showHelp: true,
                text: {
                    backButton: 'Back',
                    closeButton: 'Close',
                    headerTitle: 'Help Topics'
                },
                topics: [
                    'console/student/freetrial',
                    //'pi/forgot_creds/next',
                    'contactsupport',
                    'console/instructor/courseregsettings',
                    'custom/text/demo/example'
                ]
            }
        }
    }));
}
window.onload = init;