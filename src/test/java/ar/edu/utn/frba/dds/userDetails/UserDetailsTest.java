package ar.edu.utn.frba.dds.userDetails;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class UserDetailsTest {
  @Test
  public void createUserDetails(){
    UserDetails userDetails = new UserDetails("Hugo","Ibarra","ibarraneta@gmail.com");

    Assertions.assertEquals("Hugo",userDetails.name());
    Assertions.assertEquals("Ibarra",userDetails.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com",userDetails.email());
  }
}
