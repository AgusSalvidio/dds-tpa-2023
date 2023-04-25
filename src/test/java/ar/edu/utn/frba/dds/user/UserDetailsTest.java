package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class UserDetailsTest {
  @Test
  public void createUserDetails() throws Exception {
    UserDetails userDetails = UserDetails.composedBy("Hugo","Ibarra","ibarraneta@gmail.com");

    Assertions.assertEquals("Hugo",userDetails.name());
    Assertions.assertEquals("Ibarra",userDetails.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com",userDetails.email());
  }
  @Test
  public void cannotCreateUserDetailsWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class,()-> UserDetails.composedBy("","Ibarra","ibarraneta@gmail.com"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class,()-> UserDetails.composedBy("Hugo","","ibarraneta@gmail.com"),"Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class,()-> UserDetails.composedBy("Hugo","Ibarra",""),"Los campos no pueden estar en blanco.");
  }




}
