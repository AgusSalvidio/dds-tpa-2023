package ar.edu.utn.frba.dds.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");

  }

  @Test
  @DisplayName("Create an user")
  public void createUser() throws Exception {

    User user = User.composedBy("ibarranetaYPF", "theBestPassword", this.userDetails());

    Assertions.assertEquals("ibarranetaYPF", user.username());
    Assertions.assertEquals("Hugo", user.name());
    Assertions.assertEquals("Ibarra", user.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com", user.email());

  }

  @Test
  @DisplayName("When creating an user with any field empty should raise error")
  public void cannotCreateUserWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> User.composedBy("", "theBestPassword", this.userDetails()), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> User.composedBy("ibarranetaYPF", "", this.userDetails()), "Los campos no pueden estar en blanco.");
  }

}
