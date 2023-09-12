package ar.edu.utn.frba.dds.addons.usercreationaddon;

import ar.edu.utn.frba.dds.addons.notificationcreationaddon.NotificationMeanCreationAddOn;
import ar.edu.utn.frba.dds.user.UserDetail;

public class UserDetailCreationAddOn {

  public UserDetail ibarra() throws Exception {
    return UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com", "0123456789", new NotificationMeanCreationAddOn().wpp());
  }

  public UserDetail basuraIntergalactica() throws Exception {
    return UserDetail.composedOf("Basura", "Intergalactica", "basuraintergalactica@gmail.com","0123456789", new NotificationMeanCreationAddOn().mail());
  }

  public UserDetail basuraIntergalactica2() throws Exception {
    return UserDetail.composedOf("Basura", "Intergalactica2", "basuraintergalactica2@gmail.com","0123456789", new NotificationMeanCreationAddOn().wpp());
  }

}
