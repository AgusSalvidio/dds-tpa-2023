package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.userDetails.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
  @Test
  public void createUser(){
    UserDetails userDetails = new UserDetails("Hugo","Ibarra","ibarraneta@gmail.com");

    User user = new User("ibarranetaYPF","theBestPassword",userDetails);

    Assertions.assertEquals("ibarranetaYPF",user.username());
    Assertions.assertEquals("theBestPassword",user.password());
    Assertions.assertEquals("Hugo",user.name());
    Assertions.assertEquals("Ibarra",user.lastname());
    Assertions.assertEquals("ibarraneta@gmail.com",user.email());

  }
}
