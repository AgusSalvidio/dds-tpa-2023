package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.demo.Demo;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.location.Department;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.location.Municipality;
import ar.edu.utn.frba.dds.location.Province;
import ar.edu.utn.frba.dds.ranking.AverageClosingTimeRanking;
import ar.edu.utn.frba.dds.ranking.GreaterDegreeOfImpactRanking;
import ar.edu.utn.frba.dds.ranking.MostReportedIncidentsRanking;
import ar.edu.utn.frba.dds.ranking.WeeklyRanking;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.serviceholder.ServiceHolder;
import ar.edu.utn.frba.dds.user.User;
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

  public List<Service> establishmentsServicesNotById(Integer id) {
    try {
      return entityManager().createQuery(
              "from " + Service.class.getName() + " where id not in "
                  + "( select services_id from establishment_service "
                  + "where Establishment_id = " + id.toString() + " )", Service.class)
          .getResultList();
    } catch (NoResultException e) {
      return null;
    }
  }


  //--------------------------------------------------------------------------------------------
  //USERS
  //--------------------------------------------------------------------------------------------
  public User userIdentifiedBy(Integer id) {
    return entityManager().find(User.class, id);
  }

  public User userNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + User.class.getName() + " x WHERE x.username = :username",
              User.class)
          .setParameter("username", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  //--------------------------------------------------------------------------------------------
  //GET BY ID
  //--------------------------------------------------------------------------------------------


  public Entity entityById(Integer id) {
    return entityManager().find(Entity.class, id);
  }

  public Province provinceById(Integer id) {
    return entityManager().find(Province.class, id);
  }

  public Department departmentById(Integer id) {
    return entityManager().find(Department.class, id);
  }

  public Municipality municipalityById(Integer id) {
    return entityManager().find(Municipality.class, id);
  }

  public Location locationById(Integer id) {
    return entityManager().find(Location.class, id);
  }

  //--------------------------------------------------------------------------------------------
  //SERVICES
  //--------------------------------------------------------------------------------------------
  public State stateIdentifiedBy(Integer id) {
    return entityManager().find(State.class, id);
  }

  public State stateNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + State.class.getName() + " x WHERE x.name = :name",
              State.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Service serviceIdentifiedBy(Integer id) {
    return entityManager().find(Service.class, id);
  }

  public Service serviceNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Service.class.getName() + " x WHERE x.name = :name",
              Service.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  //--------------------------------------------------------------------------------------------
  //LOCATIONS
  //--------------------------------------------------------------------------------------------
  public Province provinceIdentifiedBy(Integer id) {
    return entityManager().find(Province.class, id);
  }

  public Province provinceNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Province.class.getName() + " x WHERE x.name = :name",
              Province.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Department departmentIdentifiedBy(Integer id) {
    return entityManager().find(Department.class, id);
  }

  public Department departmentNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Department.class.getName() + " x WHERE x.name = :name",
              Department.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Municipality municipalityIdentifiedBy(Integer id) {
    return entityManager().find(Municipality.class, id);
  }

  public Municipality municipalityNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Municipality.class.getName() + " x WHERE x.name = :name",
              Municipality.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Location locationIdentifiedBy(Integer id) {
    return entityManager().find(Location.class, id);
  }

  //--------------------------------------------------------------------------------------------
  //ESTABLISHMENTS
  //--------------------------------------------------------------------------------------------
  public EstablishmentType establishmentTypeIdentifiedBy(Integer id) {
    return entityManager().find(EstablishmentType.class, id);
  }

  public EstablishmentType establishmentTypeNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + EstablishmentType.class.getName() + " x WHERE x.name = :name",
              EstablishmentType.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Establishment establishmentIdentifiedBy(Integer id) {
    return entityManager().find(Establishment.class, id);
  }

  public Establishment establishmentNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Establishment.class.getName() + " x WHERE x.name = :name",
              Establishment.class)
          .setParameter("name", name)
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

  public EntityType entityTypeNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + EntityType.class.getName() + " x WHERE x.name = :name",
              EntityType.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public EntityName entityNameIdentifiedBy(Integer id) {
    return entityManager().find(EntityName.class, id);
  }

  public EntityName entityNameNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + EntityName.class.getName() + " x WHERE x.name = :name",
              EntityName.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public Entity entityIdentifiedBy(Integer id) {
    return entityManager().find(Entity.class, id);
  }

  public Entity entityNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Entity.class.getName() + " x WHERE x.name = :name",
              Entity.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  //--------------------------------------------------------------------------------------------
  //COMMUNITIES
  //--------------------------------------------------------------------------------------------
  public Community communityIdentifiedBy(Integer id) {
    return entityManager().find(Community.class, id);
  }

  public Community communityNamed(String name) {
    try {
      return entityManager().createQuery(
              "SELECT x FROM " + Community.class.getName() + " x WHERE x.name = :name",
              Community.class)
          .setParameter("name", name)
          .getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public List<Community> communitiesByUser() {
    return entityManager().createQuery("from " + Community.class.getName()).getResultList();
  }

  //--------------------------------------------------------------------------------------------
  //REVISAR
  //--------------------------------------------------------------------------------------------
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


  public List<Entity> entities() {
    return entityManager().createQuery("from " + Entity.class.getName()).getResultList();
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

