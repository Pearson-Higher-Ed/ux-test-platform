/**
 * Created by umahaea on 8/3/16.
 */

function init() {
  // Demo eventing API
  document.body.dispatchEvent(new CustomEvent('o.InitAvatarDisplay', {
      detail: { elementId: 'avatar-target', avatarURLText: 'http://keenthemes.com/preview/metronic/theme/assets/pages/media/profile/profile_user.jpg', avatarAltText: 'Avatar Image', avatarSize: 'large' }
    }
  ));
}
window.onload = init;
