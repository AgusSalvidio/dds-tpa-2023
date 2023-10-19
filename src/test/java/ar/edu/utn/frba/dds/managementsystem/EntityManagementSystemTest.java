package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.persistence.EntityTransaction;

public class EntityManagementSystemTest implements WithSimplePersistenceUnit {

  private MemoryBasedPersistenceSystem persistenceSystem() throws Exception {
    return this.relationalDatabasePersistenceSystem();
  }

  private MemoryBasedPersistenceSystem relationalDatabasePersistenceSystem() throws Exception {
    return new MemoryBasedPersistenceSystem();
  }

  public EstablishmentType establishmentType() {
    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName("test");
    return establishmentType;
  }

  public Municipality municipality() {
    Municipality municipality = new Municipality();
    municipality.setNombre("CABA");
    return municipality;
  }

  public Location location() {
    Location location = new Location();
    location.setStreet("AV. FIGUEROA ALCORTA Y AV. PUEYRREDON");
    location.setNumber(100);
    location.setMunicipality(this.municipality());
    return location;
  }

  public EntityName entityName() {
    EntityName entityName = new EntityName();
    entityName.setName("SUBTE H");
    return entityName;
  }

  public EntityName entityNameTwo() {
    EntityName entityName = new EntityName();
    entityName.setName("Test");
    return entityName;
  }

  @AfterEach
  public void cleanDataBase() {
    //Cleaning after running test
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().createQuery("DELETE FROM Entity").executeUpdate();
    entityManager().createQuery("DELETE FROM TransportLine").executeUpdate();
    entityManager().createQuery("DELETE FROM EntityName").executeUpdate();
    entityManager().createQuery("DELETE FROM Establishment").executeUpdate();
    entityManager().createQuery("DELETE FROM Location").executeUpdate();
    entityManager().createQuery("DELETE FROM Incident").executeUpdate();
    entityManager().createQuery("DELETE FROM Service").executeUpdate();
    entityManager().createQuery("DELETE FROM Escalator").executeUpdate();
    entityManager().createQuery("DELETE FROM Elevator").executeUpdate();
    entityManager().createQuery("DELETE FROM Toilet").executeUpdate();
    entityManager().createQuery("DELETE FROM State").executeUpdate();
    entityManager().createQuery("DELETE FROM User").executeUpdate();
    entityManager().createQuery("DELETE FROM UserDetail").executeUpdate();
    transaction.commit();
    entityManager().close();
  }

  @Test
  @DisplayName("Start managing an entity")
  public void startManagingAnEntityTest() throws Exception {
    EntityManagementSystem entityManagementSystem = Mockito.spy(EntityManagementSystem.workingWith(this.persistenceSystem()));
    EstablishmentType establishmentType = this.establishmentType();
    EntityName entityName = this.entityName();
    Location location = this.location();
    Establishment departure = Establishment.composedOf(establishmentType, "departure", location);
    Establishment arrival = Establishment.composedOf(establishmentType, "arrival", location);
    TransportLine transportLine = TransportLine.composedOf(departure, arrival, Direction.FORWARD);
    transportLine.setName(entityName);

    Mockito.doAnswer(invocation -> {
      EntityTransaction transaction = entityManager().getTransaction();

      TransportLine obtainedTransportLine = invocation.getArgument(0);
      EntityName obtainedEntityName = obtainedTransportLine.getName();
      Establishment obtainedDeparture = obtainedTransportLine.getDeparture();
      Establishment obtainedArrival = obtainedTransportLine.getArrival();
      Location obtainedLocation = obtainedArrival.getLocation();

      transaction.begin();
      entityManager().persist(obtainedLocation);
      entityManager().persist(obtainedDeparture);
      entityManager().persist(obtainedArrival);
      entityManager().persist(obtainedEntityName);
      entityManager().persist(obtainedTransportLine);
      transaction.commit();
      entityManager().close();
      return null;
    }).when(entityManagementSystem).startManagingTransportLine(transportLine);

    entityManagementSystem.startManagingTransportLine(transportLine);

    String jpql = "SELECT e FROM Entity e WHERE e.name = :name";

    Entity registeredEntity = entityManager().createQuery(jpql, Entity.class)
        .setParameter("name", transportLine.getName())
        .getSingleResult();

    /*Assertions.assertEquals(entityManagementSystem.entities().size(), 1);
    Assertions.assertEquals(entityManagementSystem.entities().get(0), registeredEntity);
    Assertions.assertEquals(entityManagementSystem.entities().get(0).incidents(), registeredEntity.incidents());*/

  }

  @Test
  @DisplayName("Stop managing an entity")
  public void stopManagingAnEntityTest() throws Exception {
    EntityManagementSystem entityManagementSystem = Mockito.spy(EntityManagementSystem.workingWith(this.persistenceSystem()));

    EstablishmentType establishmentType = this.establishmentType();
    EntityName entityName = this.entityName();
    Location location = this.location();
    Establishment departure = Establishment.composedOf(establishmentType, "departure", location);
    Establishment arrival = Establishment.composedOf(establishmentType, "arrival", location);
    TransportLine transportLine = TransportLine.composedOf(departure, arrival, Direction.FORWARD);
    transportLine.setName(entityName);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(location);
    entityManager().persist(departure);
    entityManager().persist(arrival);
    entityManager().persist(entityName);
    entityManager().persist(transportLine);
    transaction.commit();
    entityManager().close();

    //Assertions.assertEquals(entityManagementSystem.entities().size(), 1);

    String jpql = "SELECT e FROM Entity e WHERE e.name = :name";

    Entity registeredEntity = entityManager().createQuery(jpql, Entity.class)
        .setParameter("name", transportLine.getName())
        .getSingleResult();

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      TransportLine obtainedTransportLine = invocation.getArgument(0);

      transact.begin();
      entityManager().remove(obtainedTransportLine);
      transact.commit();
      entityManager().close();
      return null;

    }).when(entityManagementSystem).stopManagingTransportLine((TransportLine) registeredEntity);

    entityManagementSystem.stopManagingTransportLine((TransportLine) registeredEntity);
    //Assertions.assertTrue(entityManagementSystem.entities().isEmpty());
  }

  @Test
  @DisplayName("Update an entity")
  public void updateAnEntity() throws Exception {
    EntityManagementSystem entityManagementSystem = Mockito.spy(EntityManagementSystem.workingWith(this.persistenceSystem()));

    EstablishmentType establishmentType = this.establishmentType();
    EntityName entityName = this.entityName();
    EntityName entityNameTwo = this.entityNameTwo();
    Location location = this.location();
    Establishment departure = Establishment.composedOf(establishmentType, "departure", location);
    Establishment arrival = Establishment.composedOf(establishmentType, "arrival", location);

    TransportLine transportLine = TransportLine.composedOf(departure, arrival, Direction.FORWARD);
    transportLine.setName(entityName);

    TransportLine updateTransportLine = TransportLine.composedOf(departure, arrival, Direction.FORWARD);
    updateTransportLine.setName(entityNameTwo);

    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(location);
    entityManager().persist(departure);
    entityManager().persist(arrival);
    entityManager().persist(entityName);
    entityManager().persist(entityNameTwo);
    entityManager().persist(transportLine);
    transaction.commit();
    entityManager().close();

    //Assertions.assertEquals(entityManagementSystem.entities().size(), 1);

    String jpql = "SELECT e FROM Entity e WHERE e.name = :name";

    Entity registeredEntity = entityManager().createQuery(jpql, Entity.class)
        .setParameter("name", transportLine.getName())
        .getSingleResult();

    /*Assertions.assertEquals(entityManagementSystem.entities().size(), 1);
    Assertions.assertEquals(registeredEntity.name(), "SUBTE H");*/

    Mockito.doAnswer(invocation -> {
      EntityTransaction transact = entityManager().getTransaction();

      Entity obtainedEntity = invocation.getArgument(0);
      Entity obtainedUpdateEntity = invocation.getArgument(1);

      obtainedEntity.synchronizedWith(obtainedUpdateEntity);

      transact.begin();
      entityManager().merge(obtainedEntity);
      transact.commit();
      return null;
    }).when(entityManagementSystem).updateWith(registeredEntity, updateTransportLine);

    entityManagementSystem.updateWith(registeredEntity, updateTransportLine);

    Entity persistedEntity = entityManager().createQuery(jpql, Entity.class)
        .setParameter("name", updateTransportLine.getName())
        .getSingleResult();

    //Assertions.assertEquals(persistedEntity.getName(), entityNameTwo);
  }
}
