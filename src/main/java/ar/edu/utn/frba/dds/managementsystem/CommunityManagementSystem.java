package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import java.util.ArrayList;
import java.util.List;

public class CommunityManagementSystem implements ManagementSystem {

  List<Object> systems;

  public CommunityManagementSystem(PersistenceSystem persistenceSystem) {
    this.systems = new ArrayList<>();
    this.systems.add(persistenceSystem);
    this.persistenceSystem().addObjectTypeToStore(Community.class.getName());
  }

  public String typeDescription() {
    return "Sistema de Administración de Comunidades";
  }

  private PersistenceSystem persistenceSystem() {
    return (PersistenceSystem) this.systems.get(0);
  }

  public static CommunityManagementSystem workingWith(PersistenceSystem persistenceSystem) {
    return new CommunityManagementSystem(persistenceSystem);
  }

  public void startManaging(Object community) {
    this.persistenceSystem().storeObjectTyped(community.getClass().getName(), community);
  }

  public void stopManaging(Object community) {
    this.persistenceSystem().removeObjectTyped(community.getClass().getName(), community);
  }

  public Community community(Community community) {
    return (Community) this.persistenceSystem()
        .findObjectTyped(community.getClass().getName(), community);
  }

  public List<Object> communities() {
    return this.persistenceSystem().objectsFrom(Community.class.getName());
  }

  public void updateWith(Object currentCommunity, Object updatedCommunity) {
    Community obtainedCommunity = (Community) this.persistenceSystem()
        .findObjectTyped(currentCommunity.getClass().getName(), currentCommunity);
    obtainedCommunity.synchronizeWith((Community) updatedCommunity);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }
}

