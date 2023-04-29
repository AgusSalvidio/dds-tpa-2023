package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.publicservice.TransportLine;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.Toilet;
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
  public void createACommunityTest() throws Exception {

    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");

    Assertions.assertEquals(community.name(), "Comunidad 1");
    Assertions.assertEquals(community.description(), "Comunidad de prueba");
    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

  }

  @Test
  @DisplayName("When creating a community with any field empty should raise error")
  public void cannotCreateCommunityWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> Community.composedOf("", "Comunidad de prueba"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> Community.composedOf("Comunidad 1", ""), "Los campos no pueden estar en blanco.");
  }

  @Test
  @DisplayName("Add a member to community")
  public void addMemberToCommunityTest() throws Exception {

    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");

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

    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");

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
  @DisplayName("Add a transport line to community")
  public void addTransportLineToCommunityTest() throws Exception {

    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    TransportLine transportLine = new TransportLine();
    community.addTransportLine(transportLine);


    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.transportLines().contains(transportLine));
  }

  @Test
  @DisplayName("Remove member from community")
  public void removeMemberCommunityTest() throws Exception {

    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");

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
  public void updateCommunityServicesTest() throws Exception {

    Community community = Community.composedOf("Comunidad 1", "Comunidad de prueba");

    Assertions.assertTrue(community.members().isEmpty());
    Assertions.assertTrue(community.moderators().isEmpty());
    Assertions.assertTrue(community.services().isEmpty());

    TransportLine transportLine = new TransportLine();
    community.addTransportLine(transportLine);
    Assertions.assertTrue(community.transportLines().contains(transportLine));

    Community updtedCommunity = Community.composedOf("Comunidad 2", "Comunidad de prueba");
    Service anotherService = new Toilet();
    Service otherService = new Elevator();

    updtedCommunity.addService(anotherService);
    updtedCommunity.addService(otherService);

    community.synchronizeWith(updtedCommunity);

    Assertions.assertTrue(community.services()
        .containsAll(new ArrayList<>(Arrays.asList(anotherService, otherService))));

  }

}
