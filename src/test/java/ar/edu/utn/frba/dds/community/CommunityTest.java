package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommunityTest {
  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");

  }

  @Test
  @DisplayName("Create a community")
  public void createACommunityTest() {

    Community community = new Community();

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

  }

  @Test
  @DisplayName("Add a member to community")
  public void addMemberToCommunityTest() throws Exception {

    Community community = new Community();

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    community.addUser(user);

    Assertions.assertTrue(community.members().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

  }

  @Test
  @DisplayName("Add a moderator member to community")
  public void addModeratorToCommunityTest() throws Exception {

    Community community = new Community();

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    community.addModerator(user);

    Assertions.assertTrue(community.members().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(community.moderators().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(community.services().isEmpty());

  }

  @Test
  @DisplayName("Add a service to community")
  public void addServiceToCommunityTest() throws Exception {

    Community community = new Community();

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    Service service = new Service("Transporte");
    community.addService(service);

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().contains(service));

  }

  @Test
  @DisplayName("Remove member from community")
  public void removeMemberCommunityTest() throws Exception {

    Community community = new Community();

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    community.addUser(user);

    Assertions.assertTrue(community.members().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    community.removeUser(user);

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

  }

  @Test
  @DisplayName("Update community services")
  public void updateCommunityServicesTest() {

    Community community = new Community();

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    Service service = new Service("Transporte");

    community.addService(service);

    Assertions.assertTrue(community.services().contains(service));

    Community updtedCommunity = new Community();
    Service anotherService = new Service("Baños");
    Service otherService = new Service("Local");

    updtedCommunity.addService(anotherService);
    updtedCommunity.addService(otherService);

    community.synchronizeWith(updtedCommunity);

    Assertions.assertTrue(community.services()
        .containsAll(new ArrayList<>(Arrays.asList(anotherService, otherService))));

  }

}
