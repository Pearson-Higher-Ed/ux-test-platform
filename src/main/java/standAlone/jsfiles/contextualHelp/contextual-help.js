let conHelpProps = {
    directTopic: undefined,
    drawerTop: "61px",
    handleHelp: function () {},
    //defaultLanguage: 'en-us',
    intl: {
        locale: 'fr'
    },
    showHelp: false,
    text: {
        backButton: 'Back',
        closeButton: 'Close',
        headerTitle: 'FRENCH'
    },
    topics: [
        'console/student/freetrial',
        'pi/forgot_creds/next',
        'contactsupport'
    ]
}

function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
        detail: {
            elementId: 'contextual-help-target',
            props: conHelpProps
        }
    }));

    const helpIndexButton = document.createElement('button');
    helpIndexButton.innerText = 'Help Index';
    helpIndexButton.onclick = function() {
        conHelpProps.directTopic = undefined;
        conHelpProps.showHelp = true;
        conHelpProps.handleHelp = function() {
            conHelpProps.directTopic = undefined;
            conHelpProps.showHelp = false;
            document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
                detail: {
                    elementId: 'contextual-help-target',
                    props: conHelpProps
                }
            }));
        };
        document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
            detail: {
                elementId: 'contextual-help-target',
                props: conHelpProps
            }
        }));
    };

    const jumpToTopicButton = document.createElement('button');
    jumpToTopicButton.innerText = 'Direct Topic';
    jumpToTopicButton.onclick = function() {
        conHelpProps.directTopic = 'contactsupport';
        conHelpProps.showHelp = true;
        conHelpProps.handleHelp = function() {
            conHelpProps.showHelp = false;
            document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
                detail: {
                    elementId: 'contextual-help-target',
                    props: conHelpProps
                }
            }));
            setTimeout(function() {
                conHelpProps.directTopic = undefined;
                document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
                    detail: {
                        elementId: 'contextual-help-target',
                        props: conHelpProps
                    }
                }));
            }, 500);
        };
        document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
            detail: {
                elementId: 'contextual-help-target',
                props: conHelpProps
            }
        }));
    }

    document.body.appendChild(jumpToTopicButton);
    document.body.appendChild(helpIndexButton);
}

window.onload = init;



/*
let conHelpProps = {
    directTopic: undefined,
    drawerTop: "61px",
    handleHelp: function () {},
    language: 'en-us',
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
        'console/instructor/courseregsettings'
    ]
}

function init() {
    const helpIndexButton = document.createElement('button');
    helpIndexButton.innerText = 'Help Index';
    helpIndexButton.onclick = function() {
        conHelpProps.directTopic = undefined;
        conHelpProps.showHelp = true;
        conHelpProps.handleHelp = () => {
            conHelpProps.directTopic = undefined;
            conHelpProps.showHelp = false;
            document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
                detail: {
                    elementId: 'contextual-help-target',
                    props: conHelpProps
                }
            }));
        }
        document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
            detail: {
                elementId: 'contextual-help-target',
                props: conHelpProps
            }
        }));
    };

    const jumpToTopicButton = document.createElement('button');
    jumpToTopicButton.innerText = 'Direct Topic';
    jumpToTopicButton.onclick = function() {
        conHelpProps.directTopic = 'contactsupport';
        conHelpProps.showHelp = true;
        conHelpProps.handleHelp = () => {
            conHelpProps.directTopic = undefined;
            conHelpProps.showHelp = false;
            document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
                detail: {
                    elementId: 'contextual-help-target',
                    props: conHelpProps
                }
            }));
        }
        document.body.dispatchEvent(new CustomEvent('o.InitComponent', {
            detail: {
                elementId: 'contextual-help-target',
                props: conHelpProps
            }
        }));
    }

    document.body.appendChild(jumpToTopicButton);
    document.body.appendChild(helpIndexButton);
}

window.onload = init;*/
