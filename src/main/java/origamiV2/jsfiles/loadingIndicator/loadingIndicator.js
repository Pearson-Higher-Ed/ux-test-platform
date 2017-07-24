var loadedExamples = {ex1: true, ex2: false, ex3: false};
function loadBody() {
    if (!loadedExamples.ex2) {
        loadedExamples.ex2 = true;
        document.body.dispatchEvent(new CustomEvent('o.initLoadingIndicator', {
            detail: {
                elementId: 'bodyAttach',
                props: {
                    id: "ex2",
                    appLevel: 'true',
                    active: 'true',
                    data: {
                        text: {
                            chipText: 'loading...'
                        }
                    }
                }
            }
        }));
    }
    else {
        document.body.dispatchEvent(new CustomEvent('o.LoadingIndicatorToggle.ex2'));
    }

    setTimeout(function () {
        document.body.dispatchEvent(new CustomEvent('o.LoadingIndicatorToggle.ex2'));
    }, 5000);
}

function loadDOM() {
    document.body.dispatchEvent(new CustomEvent('o.initLoadingIndicator', {
        detail: {
            elementId: 'loaderAttach',
            props: {id: 'ex3', active: 'true', data: {text: {chipText: 'loading...'}}}
        }
    }));
    document.getElementById('domLoader').disabled = true;
    loadedExamples.ex3 = true;
}
function toggleLoader(loaderId) {
    document.body.dispatchEvent(new CustomEvent('o.LoadingIndicatorToggle.' + loaderId));
}
