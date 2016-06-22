/**
 * Created by umahaea on 6/10/16.
 */

document.addEventListener("DOMContentLoaded", function() {
  document.dispatchEvent(new CustomEvent('o.InitAllDrawerElements'));
});

document.addEventListener('oDrawer.open', function (e) {
  document.getElementById("toggleStatusText").innerHTML = "Drawer is opened";
});

document.addEventListener('oDrawer.close', function (e) {
  document.getElementById("toggleStatusText").innerHTML = "Drawer is closed";
});