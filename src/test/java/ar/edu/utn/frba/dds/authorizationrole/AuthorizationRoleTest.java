package ar.edu.utn.frba.dds.authorizationrole;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorizationRoleTest {
  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();

  }

  @Test
  @DisplayName("Create an authorization role")
  public void createAnAuthorizatioRoleTest() throws Exception {

    User user = this.ibarraneta();
    AuthorizationRole authorizationRole = AuthorizationRole.composedOf(user, "Administrador");

    Assertions.assertEquals(authorizationRole.user(), user);
    Assertions.assertEquals(authorizationRole.role(), "Administrador");

  }
}
