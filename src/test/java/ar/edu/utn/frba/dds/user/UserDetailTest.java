package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.addons.notificationcreationaddon.NotificationMeanCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class UserDetailTest {
  @Test
  @DisplayName("Create user details")
  public void createUserDetails() throws Exception {
    UserDetail userDetail = UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com", "0123456789", new NotificationMeanCreationAddOn().wpp());

    Assertions.assertEquals("Hugo", userDetail.name());
    Assertions.assertEquals("Ibarra", userDetail.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com", userDetail.email());
    Assertions.assertEquals("0123456789", userDetail.telephone());
    Assertions.assertTrue(userDetail.services().isEmpty());
    Assertions.assertTrue(userDetail.entities().isEmpty());
  }

  @Test
  @DisplayName("When creating an user details with any empty field should raise error")
  public void cannotCreateUserDetailsWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("", "Ibarra", "ibarraneta@gmail.com", "0123456789", new NotificationMeanCreationAddOn().wpp()), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("Hugo", "", "ibarraneta@gmail.com", "0123456789", new NotificationMeanCreationAddOn().wpp()), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("Hugo", "Ibarra", "", "0123456789", new NotificationMeanCreationAddOn().wpp()), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> UserDetail.composedOf("Hugo", "Ibarra", "ibarraneta@gmail.com", "", new NotificationMeanCreationAddOn().wpp()), "Los campos no pueden estar en blanco.");
  }


}
