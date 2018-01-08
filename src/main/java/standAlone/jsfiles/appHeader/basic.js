document.addEventListener('DOMContentLoaded', function() {var element = document.querySelector('.demo-container');var config = {"mode":"Basic","user":{"givenName":"Michel"},"courseItems":[{"text":"Physics","href":"https://example.com/physics"},{"text":"Chemistry","href":"https://example.com/chemistry"},{"text":"Maths","href":"https://example.com/maths"}]};console.info(config);

    document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {
        detail: {
            element: element,
            config: config
        }
    }));
});
