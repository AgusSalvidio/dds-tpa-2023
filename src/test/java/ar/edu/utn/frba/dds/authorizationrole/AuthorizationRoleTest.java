package ar.edu.utn.frba.dds.authorizationrole;

import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthorizationRoleTest {
  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");

  }

  @Test
  @DisplayName("Create an authorization role")
  public void createAnAuthorizatioRoleTest() throws Exception {

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    AuthorizationRole authorizationRole = AuthorizationRole.composedOf(user, "Administrador");

    Assertions.assertEquals(authorizationRole.user(), user);
    Assertions.assertEquals(authorizationRole.role(), "Administrador");

  }
}
