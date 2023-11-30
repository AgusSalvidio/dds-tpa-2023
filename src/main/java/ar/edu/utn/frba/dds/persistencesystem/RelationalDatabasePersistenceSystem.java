package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.demo.Demo;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.entity.TransportType;
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
  //USERS
  //--------------------------------------------------------------------------------------------
  public List<User> users() {
    return entityManager().createQuery("from " + User.class.getName()).getResultList();
  }

  public User userIdentifiedBy(Integer anUserId) {
    return entityManager().find(User.class, anUserId);
  }

  public void startManagingUser(User anUser) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anUser);
    transaction.commit();
  }

  public void updateManagingUser(User anUser) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().merge(anUser);
    transaction.commit();
  }

  public void stopManagingUser(User anUser) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anUser);
    transaction.commit();
  }

  public void startManagingUserDetail(UserDetail anUserDetail) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anUserDetail);
    transaction.commit();
  }

  public void updateManagingUserDetail(UserDetail anUserDetail) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().merge(anUserDetail);
    transaction.commit();
  }

  public void stopManagingUserDetail(UserDetail anUserDetail) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anUserDetail);
    transaction.commit();
  }

  public User userNamed(String anUserName) {
    try {
      return entityManager().createQuery(
              "SELECT u FROM " + User.class.getName() + " u WHERE u.username = :username",
              User.class)
          .setParameter("username", anUserName)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public List<UserDetail> userDetails() {
    return entityManager().createQuery("from " + UserDetail.class.getName()).getResultList();
  }

  //--------------------------------------------------------------------------------------------
  //GENERIC
  //--------------------------------------------------------------------------------------------
  public List<Object> objectList(String className) {
    return entityManager().createQuery("from " + className).getResultList();
  }

  public void startManaging(Object object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().persist(object);
    transaction.commit();
  }

  public void stopManaging(Object object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().remove(object);
    transaction.commit();
  }

  //--------------------------------------------------------------------------------------------
  //GET BY ID
  //--------------------------------------------------------------------------------------------
  public EntityType entityTypeById(Integer id) {
    return entityManager().find(EntityType.class, id);
  }

  public EstablishmentType establishmentTypeById(Integer id) {
    return entityManager().find(EstablishmentType.class, id);
  }

  public TransportType transportTypeById(Integer id) {
    return entityManager().find(TransportType.class, id);
  }

  public Entity entityById(Integer id) {
    return entityManager().find(Entity.class, id);
  }


  //--------------------------------------------------------------------------------------------
  //ENTITY-TYPE
  //--------------------------------------------------------------------------------------------
  public List<EntityType> entityTypes() {
    return entityManager().createQuery("from " + EntityType.class.getName()).getResultList();
  }

  public void startManagingEntityType(EntityType object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().persist(object);
    transaction.commit();
  }

  public void stopManagingEntityType(EntityType object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().remove(object);
    transaction.commit();
  }

  //--------------------------------------------------------------------------------------------
  //TRANSPORTATION-TYPE
  //--------------------------------------------------------------------------------------------
  public List<TransportType> transportTypes() {
    return entityManager().createQuery("from " + TransportType.class.getName()).getResultList();
  }

  public void startManagingTransportType(TransportType object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().persist(object);
    transaction.commit();
  }

  public void stopManagingTransportType(TransportType object) {
    EntityTransaction transaction = entityManager().getTransaction();
    transaction.begin();
    entityManager().remove(object);
    transaction.commit();
  }


  public void startManagingElevator(Elevator anElevator) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anElevator);
    transaction.commit();
  }

  public void startManagingEscalator(Escalator anEscalator) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anEscalator);
    transaction.commit();
  }

  public void startManagingToilet(Toilet toilet) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(toilet);
    transaction.commit();
  }

  public void stopManagingEscalator(Escalator anEscalator) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anEscalator);
    transaction.commit();
  }

  public void stopManagingElevator(Elevator anElevator) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anElevator);
    transaction.commit();
  }

  public void stopManagingToilet(Toilet toilet) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(toilet);
    transaction.commit();
  }

  public void startManagingState(State state) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(state);
    transaction.commit();
  }

  public void stopManagingState(State state) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(state);
    transaction.commit();
  }

  public List<Service> services() {
    return entityManager().createQuery("from " + Service.class.getName()).getResultList();
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
  public Service serviceIdentifiedBy(Integer serviceId) {
    //return this.demo.services().stream().
    // filter(service -> service.getId().equals(serviceId)).findFirst().orElse(null);
    return null;
  }

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

