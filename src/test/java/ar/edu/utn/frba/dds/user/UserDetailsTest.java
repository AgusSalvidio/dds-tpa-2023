package ar.edu.utn.frba.dds.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class UserDetailsTest {
  @Test
  @DisplayName("Create user details")
  public void createUserDetails() throws Exception {
    UserDetails userDetails = UserDetails.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com");

    Assertions.assertEquals("Hugo", userDetails.name());
    Assertions.assertEquals("Ibarra", userDetails.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com", userDetails.email());
    Assertions.assertTrue(userDetails.services().isEmpty());
    Assertions.assertTrue(userDetails.transports().isEmpty());
  }

  @Test
  @DisplayName("When creating an user details with any empty field should raise error")
  public void cannotCreateUserDetailsWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> UserDetails.composedOf("", "Ibarra", "ibarraneta@gmail.com"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetails.composedOf("Hugo", "", "ibarraneta@gmail.com"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetails.composedOf("Hugo", "Ibarra", ""), "Los campos no pueden estar en blanco.");
  }


}
