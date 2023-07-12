package ar.edu.utn.frba.dds.addons.usercreationaddon;

import ar.edu.utn.frba.dds.user.UserDetails;

public class UserDetailsCreationAddOn {

  public UserDetails ibarra() throws Exception {
    return UserDetails.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com");
  }

  public UserDetails basuraIntergalactica() throws Exception {
    return UserDetails.composedOf("Basura", "Intergalactica", "basuraintergalactica@gmail.com");
  }

  public UserDetails basuraIntergalactica2() throws Exception {
    return UserDetails.composedOf("Basura", "Intergalactica2", "basuraintergalactica2@gmail.com");
  }

}
