package ar.edu.utn.frba.dds.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class UserDetailTest {
  @Test
  @DisplayName("Create user details")
  public void createUserDetails() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com");

    Assertions.assertEquals("Hugo", userDetail.name());
    Assertions.assertEquals("Ibarra", userDetail.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com", userDetail.email());
    Assertions.assertTrue(userDetail.services().isEmpty());
    Assertions.assertTrue(userDetail.entities().isEmpty());
  }

  @Test
  @DisplayName("When creating an user details with any empty field should raise error")
  public void cannotCreateUserDetailsWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("", "Ibarra", "ibarraneta@gmail.com"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("Hugo", "", "ibarraneta@gmail.com"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("Hugo", "Ibarra", ""), "Los campos no pueden estar en blanco.");
  }


}
