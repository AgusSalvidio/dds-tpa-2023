package ar.edu.utn.frba.dds.addons.usercreationaddon;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.user.User;

public class UserCreationAddOn {

  public User ibarraneta() throws Exception {
    return User.composedOf(
        "ibarranetaYPF",
        "theBestPassword",
        new UserDetailCreationAddOn().ibarra(), AuthorizationRole.ADMINISTRADOR);
  }

  public User basuraIntergalactica() throws Exception {
    return User.composedOf(
        "basuraIntergalactica",
        "intergalacticGarbageCollector",
        new UserDetailCreationAddOn().basuraIntergalactica(), AuthorizationRole.ADMINISTRADOR);
  }

  public User basuraIntergalactica2() throws Exception {
    return User.composedOf(
        "basuraIntergalactica2",
        "intergalacticGarbageCollector2",
        new UserDetailCreationAddOn().basuraIntergalactica2(), AuthorizationRole.ADMINISTRADOR);
  }
}
