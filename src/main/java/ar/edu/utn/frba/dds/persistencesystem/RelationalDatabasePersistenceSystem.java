package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import javax.persistence.EntityTransaction;

public class RelationalDatabasePersistenceSystem implements WithSimplePersistenceUnit {
  public void startManagingUser(User anUser) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().persist(anUser);
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

  public void stopManagingUserDetail(UserDetail anUserDetail) {
    EntityTransaction transaction = entityManager().getTransaction();

    transaction.begin();
    entityManager().remove(anUserDetail);
    transaction.commit();
  }

  public List<User> users() {
    return entityManager().createQuery("from " + User.class.getName()).getResultList();
  }

  public List<UserDetail> userDetails() {
    return entityManager().createQuery("from " + UserDetail.class.getName()).getResultList();
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

}
