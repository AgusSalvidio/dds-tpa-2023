package ar.edu.utn.frba.dds.addons.usercreationaddon;

import ar.edu.utn.frba.dds.user.UserDetail;

public class UserDetailsCreationAddOn {

  public UserDetail ibarra() throws Exception {
    return UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com");
  }

  public UserDetail basuraIntergalactica() throws Exception {
    return UserDetail.composedOf("Basura", "Intergalactica", "basuraintergalactica@gmail.com");
  }

  public UserDetails basuraIntergalactica2() throws Exception {
    return UserDetails.composedOf("Basura", "Intergalactica2", "basuraintergalactica2@gmail.com");
  }

}
