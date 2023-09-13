package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserDetailCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
  private UserDetail userDetails() throws Exception {
    return new UserDetailCreationAddOn().ibarra();

  }

  @Test
  @DisplayName("Create an user")
  public void createUser() throws Exception {

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());

    Assertions.assertEquals("ibarranetaYPF", user.username());
    Assertions.assertEquals("Hugo", user.name());
    Assertions.assertEquals("Ibarra", user.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com", user.email());
    Assertions.assertEquals("0123456789", user.telephone());

  }

  @Test
  @DisplayName("When creating an user with any field empty should raise error")
  public void cannotCreateUserWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> User.composedOf("", "theBestPassword", this.userDetails()), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> User.composedOf("ibarranetaYPF", "", this.userDetails()), "Los campos no pueden estar en blanco.");
  }

}
