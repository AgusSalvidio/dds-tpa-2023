package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.addons.communitycreationaddon.CommunityCreationAddOn;
import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommunityManagementSystemTest {
  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();
  }

  private PersistenceSystem persistenceSystem() {
    return this.memoryBasedPersistenceSystem();
  }

  private MemoryBasedPersistenceSystem memoryBasedPersistenceSystem() {
    return new MemoryBasedPersistenceSystem();
  }

  private Community community() throws Exception {
    return new CommunityCreationAddOn().firstCommunity();
  }

  @Test
  @DisplayName("Start managing a community")
  public void startManagingACommunityTest() throws Exception {

    CommunityManagementSystem communityManagementSystem = CommunityManagementSystem.workingWith(this.persistenceSystem());
    Community community = this.community();

    Assertions.assertTrue(communityManagementSystem.communities().isEmpty());

    communityManagementSystem.startManaging(community);

    Assertions.assertEquals(communityManagementSystem.community(community), community);

  }

  @Test
  @DisplayName("Stop managing a community")
  public void stopManagingACommunityTest() throws Exception {

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

    updatedCommunity.addTransportLine(transportLine);

    communityManagementSystem.updateWith(community, updatedCommunity);

    Assertions.assertEquals(communityManagementSystem.community(community), community);
  }


}

