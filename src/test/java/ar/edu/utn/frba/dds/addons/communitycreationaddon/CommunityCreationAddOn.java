package ar.edu.utn.frba.dds.addons.communitycreationaddon;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.user.User;

public class CommunityCreationAddOn {

  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();
  }

  private User basuraIntergalactica() throws Exception {
    return new UserCreationAddOn().basuraIntergalactica();
  }

  private User basuraIntergalactica2() throws Exception {
    return new UserCreationAddOn().basuraIntergalactica2();
  }

  public Community firstCommunity() throws Exception {
    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");
    community.addModerator(this.ibarraneta());
    community.addUser(this.basuraIntergalactica());
    return community;
  }

  public Community secondCommunity() throws Exception {
    Community community = Community.composedOf("Comunidad 2", "Comunidad de prueba");
    community.addModerator(this.ibarraneta());
    community.addUser(this.basuraIntergalactica());
    community.addAffected(this.basuraIntergalactica2());
    return community;
  }

}
