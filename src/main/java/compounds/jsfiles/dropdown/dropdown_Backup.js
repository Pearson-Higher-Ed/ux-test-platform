function init() {
    document.body.dispatchEvent(new CustomEvent('o.InitComponents', {detail:{elementId:'dropdown-target',componentName:'Dropdown',props:{presentationType:'label',presentationText:'label',list:['Thing one', 'Thing two'],mobileTitle:'Mobile title',changeHandler:function (item) {console.log(item)},dropdownControlLabel:'This is a label dropdown'}}}}}));
}
window.onload = init;