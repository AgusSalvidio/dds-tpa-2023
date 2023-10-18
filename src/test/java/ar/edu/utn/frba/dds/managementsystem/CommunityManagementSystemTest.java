package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.persistence.EntityTransaction;

public class CommunityManagementSystemTest implements WithSimplePersistenceUnit {

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.relationalDatabasePersistenceSystem();
  }

  private RelationalDatabasePersistenceSystem relationalDatabasePersistenceSystem() {
    return new RelationalDatabasePersistenceSystem();
  }

  private Community community() {
    return Community.composedOf("Comunidad 1", "Comunidad de prueba");
  }

  private Community communityTwo() {
    return Community.composedOf("Comunidad 2", "Comunidad de prueba");
  }

  @AfterEach
  public void cleanDataBase() {
    //Cleaning after running test
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().createQuery("DELETE FROM Community").executeUpdate();
    transaction.commit();
    entityManager().close();
  }

  @Test
  @DisplayName("Start managing a community")
  public void startManagingACommunityTest() throws Exception {

    CommunityManagementSystem communityManagementSystem = Mockito.spy(CommunityManagementSystem.workingWith(this.persistenceSystem()));
    Community community = this.community();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      Community obtainedCommunity = invocation.getArgument(0);

      transaction.begin();
      entityManager().persist(obtainedCommunity);
      transaction.commit();
      entityManager().close();
      return null;
    }).when(communityManagementSystem).startManaging(community);

    communityManagementSystem.startManaging(community);

    String jpql = "SELECT c FROM Community c WHERE c.name = :name";

    Community registeredCommunity = entityManager().createQuery(jpql, Community.class)
        .setParameter("name", community.name())
        .getSingleResult();

    Assertions.assertEquals(communityManagementSystem.communities().size(), 1);
    Assertions.assertEquals(communityManagementSystem.communities().get(0), registeredCommunity);
  }

  @Test
  @DisplayName("Stop managing a community")
  public void stopManagingACommunityTest() throws Exception {

    CommunityManagementSystem communityManagementSystem = Mockito.spy(CommunityManagementSystem.workingWith(this.persistenceSystem()));
    Community community = this.community();

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(community);
    transaction.commit();

    Assertions.assertEquals(communityManagementSystem.communities().size(), 1);

    String jpql = "SELECT c FROM Community c WHERE c.name = :name";

    Community registeredCommunity = entityManager().createQuery(jpql, Community.class)
        .setParameter("name", community.name())
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Community obtainedCommunity = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedCommunity);
      transact.commit();
      return null;
    }).when(communityManagementSystem).stopManaging(community);

    communityManagementSystem.stopManaging(registeredCommunity);
    Assertions.assertTrue(communityManagementSystem.communities().isEmpty());
  }

  @Test
  @DisplayName("Update a community")
  public void updateCommunityTest() throws Exception {

    CommunityManagementSystem communityManagementSystem = Mockito.spy(CommunityManagementSystem.workingWith(this.persistenceSystem()));
    Community community = this.community();
    Community updateCommunity = this.communityTwo();

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(community);
    transaction.commit();

    String jpql = "SELECT c FROM Community c WHERE c.name = :name";

    Community registeredCommunity = entityManager().createQuery(jpql, Community.class)
        .setParameter("name", community.name())
        .getSingleResult();

    Assertions.assertEquals(communityManagementSystem.communities().size(), 1);
    Assertions.assertEquals(registeredCommunity.name(), "Comunidad 1");

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Community obtainedCommunity = invocation.getArgument(0);
      Community obtainedUpdateCommunity = invocation.getArgument(1);

      obtainedCommunity.synchronizeWith(obtainedUpdateCommunity);

      transact.begin();
      entityManager().merge(obtainedCommunity);
      transact.commit();
      return null;
    }).when(communityManagementSystem).updateWith(registeredCommunity, updateCommunity);

    Community persistedCommunity = entityManager().createQuery(jpql, Community.class)
        .setParameter("name", "Comunidad 1")
        .getSingleResult();

    Assertions.assertEquals(persistedCommunity.name(), "Comunidad 1");
  }


}

