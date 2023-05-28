package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserDetailsCreationAddOn;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {
  private UserDetails userDetails() throws Exception {
    return new UserDetailsCreationAddOn().ibarra();
  }

  @Test
  @DisplayName("Create a member")
  public void createAMemberTest() throws Exception {

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    Member member = Member.composedOf(user, "Suscriptor");

    Assertions.assertEquals(member.user(), user);
    Assertions.assertEquals(member.role(), "Suscriptor");

  }

  @Test
  @DisplayName("Create a moderator member")
  public void createAModeratorMemberTest() throws Exception {

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    Member member = Member.composedOf(user, "Moderador");

    Assertions.assertEquals(member.user(), user);
    Assertions.assertEquals(member.role(), "Moderador");

  }


}
