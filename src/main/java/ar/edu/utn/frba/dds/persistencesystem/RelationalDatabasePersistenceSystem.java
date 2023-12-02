package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.demo.Demo;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.entity.TransportType;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.ranking.AverageClosingTimeRanking;
import ar.edu.utn.frba.dds.ranking.GreaterDegreeOfImpactRanking;
import ar.edu.utn.frba.dds.ranking.MostReportedIncidentsRanking;
import ar.edu.utn.frba.dds.ranking.WeeklyRanking;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

public class RelationalDatabasePersistenceSystem implements WithSimplePersistenceUnit {

  Demo demo = new Demo();

  public RelationalDatabasePersistenceSystem() throws Exception {
    entityManager().clear();
    this.demo.initialize(this);
  }

  //--------------------------------------------------------------------------------------------
  //GENERIC
  //--------------------------------------------------------------------------------------------
  public void startManaging(Object object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().persist(object);
    transaction.commit();
  }

  public void updateManaging(Object object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().merge(object);
    transaction.commit();
  }

  public void stopManaging(Object object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().remove(object);
    transaction.commit();
  }

  public List<Object> objectList(String className) {
    return entityManager().createQuery("from " + className).getResultList();
  }

  //--------------------------------------------------------------------------------------------
  //USERS
  //--------------------------------------------------------------------------------------------
  public User userIdentifiedBy(Integer anUserId) {
    return entityManager().find(User.class, anUserId);
  }

  public User userNamed(String anUserName) {
    try {
      return entityManager().createQuery(
                      "SELECT x FROM " + User.class.getName() + " x WHERE x.username = :username",
                      User.class)
              .setParameter("username", anUserName)
              .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  //--------------------------------------------------------------------------------------------
  //SERVICES
  //--------------------------------------------------------------------------------------------
  public State stateIdentifiedBy(Integer stateId) {
    return entityManager().find(State.class, stateId);
  }

  public State stateNamed(String stateName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + State.class.getName() + " x WHERE x.name = :name",
              State.class)
          .setParameter("name", stateName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Service serviceIdentifiedBy(Integer serviceId) {
    return entityManager().find(Service.class, serviceId);
  }

  public Service serviceNamed(String serviceName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Service.class.getName() + " x WHERE x.name = :name",
              Service.class)
          .setParameter("name", serviceName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  //--------------------------------------------------------------------------------------------
  //ESTABLISHMENTS
  //--------------------------------------------------------------------------------------------
  public EstablishmentType establishmentTypeIdentifiedBy(Integer id) {
    return entityManager().find(EstablishmentType.class, id);
  }

  public EstablishmentType establishmentTypeNamed(String establishmentTypeName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + EstablishmentType.class.getName() + " x WHERE x.name = :name",
              EstablishmentType.class)
          .setParameter("name", establishmentTypeName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Establishment establishmentIdentifiedBy(Integer id) {
    return entityManager().find(Establishment.class, id);
  }

  public Establishment establishmentNamed(String establishmentName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Establishment.class.getName() + " x WHERE x.name = :name",
              Establishment.class)
          .setParameter("name", establishmentName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  //--------------------------------------------------------------------------------------------
  //ENTITIES
  //--------------------------------------------------------------------------------------------
  public EntityType entityTypeIdentifiedBy(Integer id) {
    return entityManager().find(EntityType.class, id);
  }

  public EntityType entityTypeNamed(String entityTypeName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + EntityType.class.getName() + " x WHERE x.name = :name",
              EntityType.class)
          .setParameter("name", entityTypeName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public EntityName entityNameIdentifiedBy(Integer id) {
    return entityManager().find(EntityName.class, id);
  }

  public EntityName entityNameNamed(String entityNameName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + EntityName.class.getName() + " x WHERE x.name = :name",
              EntityName.class)
          .setParameter("name", entityNameName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Entity entityIdentifiedBy(Integer entityId) {
    return entityManager().find(Entity.class, entityId);
  }

  public Entity entityNamed(String entityName) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Entity.class.getName() + " x WHERE x.name = :name",
              Entity.class)
          .setParameter("name", entityName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public void startManagingAuthorizationRole(AuthorizationRole authorizationRole) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().persist(authorizationRole);
    transaction.commit();
  }

  public void stopManagingAuthorizationRole(AuthorizationRole authorizationRole) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(authorizationRole);
    transaction.commit();
  }

  public List<AuthorizationRole> roles() {
    return entityManager().createQuery("from " + AuthorizationRole.class.getName()).getResultList();
  }

  public void startManagingIncident(Incident anIncident) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anIncident);
    transaction.commit();
  }

  public void stopManagingIncident(Incident anIncident) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anIncident);
    transaction.commit();
  }

  public List<Incident> incidents() {
    return entityManager().createQuery("from " + Incident.class.getName()).getResultList();
  }

  public void startManagingTransportLine(TransportLine transportLine) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(transportLine);
    transaction.commit();
  }

  public void stopManagingTransportLine(TransportLine transportLine) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(transportLine);
    transaction.commit();
  }

  public List<Entity> entities() {
    return entityManager().createQuery("from " + Entity.class.getName()).getResultList();
  }

  public void startManagingCommunity(Community community) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(community);
    transaction.commit();
  }

  public void stopManagingCommunity(Community community) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(community);
    transaction.commit();
  }

  public List<Community> communities() {
    return entityManager().createQuery("from " + Community.class.getName()).getResultList();
  }

  public void startManagingIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anIncidentPerCommunity);
    transaction.commit();
  }

  public void stopManagingIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anIncidentPerCommunity);
    transaction.commit();
  }

  public List<IncidentPerCommunity> incidentsPerCommunity() {
    return entityManager()
        .createQuery("from " + IncidentPerCommunity.class.getName()).getResultList();
  }

  //Agregados por FDM, faltaban todos
  //Hay que desarrollarlos pq no hay nada-!!!!!!!!!!!!!!!!!
  //Menos Mal que estoy escuchndo IRON MAIDEN

  public Community communityIdentifiedBy(Integer communityId) {
    //return this.demo.communities().stream().
    // filter(community -> community.getId().equals(communityId)).findFirst().orElse(null);
    return null;
  }

  public IncidentPerCommunity incidentPerCommunityIdentifiedBy(Integer anId) {
    //return this.demo.incidentPerCommunities().stream().
    // filter(incidentPerCommunity ->
    // incidentPerCommunity.getId().equals(anId)).findFirst().orElse(null);
    return null;
  }

  public List<IncidentPerCommunity> incidentsPerCommunityFilteredBy(String state) {
    /*
    if (Objects.equals(state, "ALL")) {
      return this.demo.incidentPerCommunities();
    } else {
      return this.demo.incidentPerCommunities().stream()
              .filter(incidentPerCommunity -> incidentPerCommunity.state().name.equals(state))
              .collect(Collectors.toList());
    }
    */
    return null;
  }

  public void closeIncidentPerCommunity(IncidentPerCommunity anIncidentPerCommunity) {
    anIncidentPerCommunity.close();
  }

  public void startManagingAverageClosingTimeRanking(
      AverageClosingTimeRanking averageClosingTimeRanking) {
    //TODO
  }

  public void stopManagingAverageClosingTimeRanking(
      AverageClosingTimeRanking averageClosingTimeRanking) {
    //TODO
  }

  public void startManagingGreaterDegreeOfImpactRanking(
      GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking) {
    //TODO
  }

  public void stopManagingGreaterDegreeOfImpactRanking(
      GreaterDegreeOfImpactRanking greaterDegreeOfImpactRanking) {
    //TODO
  }

  public void startManagingMostReportedIncidentRanking(
      MostReportedIncidentsRanking mostReportedIncidentsRanking) {
    //TODO
  }

  public void stopManagingMostReportedIncidentRanking(
      MostReportedIncidentsRanking mostReportedIncidentsRanking) {
    //TODO
  }

  public List<WeeklyRanking> rankings() {
    //return this.demo.rankings();
    return null;
  }

  public List<ServiceHolder> serviceHolders() {
    //return this.demo.serviceHolders();
    return null;
  }

  public void startManagingServiceHolder(ServiceHolder serviceHolder) {
    //this.demo.serviceHolders().add(serviceHolder);
    //TODO
  }


}

