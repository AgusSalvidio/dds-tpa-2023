package ar.edu.utn.frba.dds.addons.usercreationaddon;

import ar.edu.utn.frba.dds.user.User;

public class UserCreationAddOn {

  public User ibarraneta() throws Exception {
    return User.composedOf(
        "ibarranetaYPF",
        "theBestPassword",
        new UserDetailsCreationAddOn().ibarra());
  }

  public User basuraIntergalactica() throws Exception {
    return User.composedOf(
        "basuraIntergalactica",
        "intergalacticGarbageCollector",
        new UserDetailsCreationAddOn().basuraIntergalactica());
  }

  public User basuraIntergalactica2() throws Exception {
    return User.composedOf(
        "basuraIntergalactica2",
        "intergalacticGarbageCollector2",
        new UserDetailsCreationAddOn().basuraIntergalactica2());
  }
}
