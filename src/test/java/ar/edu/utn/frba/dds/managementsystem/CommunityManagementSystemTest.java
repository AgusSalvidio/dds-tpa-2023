package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.publicservice.TransportLine;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommunityManagementSystemTest {
  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");
  }

  private PersistenceSystem persistenceSystem() {
    return this.memoryBasedPersistenceSystem();
  }

  private MemoryBasedPersistenceSystem memoryBasedPersistenceSystem() {
    return new MemoryBasedPersistenceSystem();
  }

  private Community community() {
    return new Community();
  }

  @Test
  @DisplayName("Start managing a community")
  public void startManagingACommunityTest() {

    CommunityManagementSystem communityManagementSystem = CommunityManagementSystem.workingWith(this.persistenceSystem());
    Community community = this.community();

    Assertions.assertTrue(communityManagementSystem.communities().isEmpty());

    communityManagementSystem.startManaging(community);

    Assertions.assertEquals(communityManagementSystem.community(community), community);

  }

  @Test
  @DisplayName("Stop managing a community")
  public void stopManagingACommunityTest() {

    CommunityManagementSystem communityManagementSystem = CommunityManagementSystem.workingWith(this.persistenceSystem());
    Community community = this.community();

    Assertions.assertTrue(communityManagementSystem.communities().isEmpty());

    communityManagementSystem.startManaging(community);

    Assertions.assertEquals(communityManagementSystem.community(community), community);

    communityManagementSystem.stopManaging(community);

    Assertions.assertTrue(communityManagementSystem.communities().isEmpty());

  }

  @Test
  @DisplayName("Update a community")
  public void updateCommunityTest() throws Exception {

    CommunityManagementSystem communityManagementSystem = CommunityManagementSystem.workingWith(this.persistenceSystem());
    Community community = this.community();

    Assertions.assertTrue(communityManagementSystem.communities().isEmpty());

    communityManagementSystem.startManaging(community);

    Assertions.assertEquals(communityManagementSystem.community(community), community);

    Community updatedCommunity = this.community();
    TransportLine transportLine = new TransportLine();
    community.addTransportLine(transportLine);
    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    community.addModerator(user);

    communityManagementSystem.updateWith(community, updatedCommunity);

    Assertions.assertEquals(communityManagementSystem.community(community), community);
  }


}

