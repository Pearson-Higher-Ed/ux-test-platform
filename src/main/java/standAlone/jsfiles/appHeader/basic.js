/**
 * Created by umahaea on 3/30/16.
 */
document.addEventListener('DOMContentLoaded', function() {

    var element = document.querySelector('.demo-container');
    var config = {"mode":"Basic","user":{"givenName":"Michel"},"courseItems":[{"text":"Physics","href":"https://example.com/physics"}]};

    console.info(config);

    document.dispatchEvent(new CustomEvent('o.DOMContentLoaded', {
        detail: {
            element: element,
            config: config
        }
    }));
});
