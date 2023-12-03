package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {
  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();
  }

  @Test
  @DisplayName("Create a member")
  public void createAMemberTest() throws Exception {

    User user = this.ibarraneta();
    Member member = Member.composedOf(user, MemberRole.SUSCRIPTOR);

    Assertions.assertEquals(member.user(), user);
    Assertions.assertEquals(member.role(), MemberRole.SUSCRIPTOR);

  }

  @Test
  @DisplayName("Create a moderator member")
  public void createAModeratorMemberTest() throws Exception {

    User user = this.ibarraneta();
    Member member = Member.composedOf(user, MemberRole.MODERADOR);

    Assertions.assertEquals(member.user(), user);
    Assertions.assertEquals(member.role(), MemberRole.MODERADOR);

  }


}
