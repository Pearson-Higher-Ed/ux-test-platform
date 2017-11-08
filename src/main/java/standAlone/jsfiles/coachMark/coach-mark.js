function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitCoachMark', {
        detail: {
            elementId: 'top',
            opts: {
                title: 'Coach Mark below feature /w Got It',
                text: 'Informative Text',
                gotIt: true,
                id: 'unique',
                disableShadowing: true
            }
        }
    }));
}
window.onload = init;
