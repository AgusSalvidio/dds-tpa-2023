package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommunityManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public CommunityManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Comunidades";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static CommunityManagementSystem workingWith(
          RelationalDatabasePersistenceSystem persistenceSystem) {
    return new CommunityManagementSystem(persistenceSystem);
  }

  public void startManaging(Community community) {
    this.persistenceSystem().startManagingCommunity(community);
  }

  public void stopManaging(Community community) {
    this.persistenceSystem().stopManagingCommunity(community);
  }

  public List<Community> communities() {
    return this.persistenceSystem().communities();
  }

  public void updateWith(Community currentCommunity, Community updatedCommunity) {
    currentCommunity.synchronizeWith(updatedCommunity);
  }

  public void startManagingCommunityForm(Map model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();

    this.startManaging(Community.composedOf(name, description));
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }

  public List<Community> communitiesForUser(User anUser) {
    return this.communities().stream()
        .filter(community -> community.members().stream()
            .anyMatch(member -> member.user().equals(anUser)))
        .collect(Collectors.toList());
  }

  public List<Service> servicesFor(User anUser) {
    return this.communities().stream()
        .filter(community -> community.members().stream()
            .anyMatch(member -> member.user().equals(anUser)))
        .flatMap(community -> community.services().stream())
        .collect(Collectors.toList());
  }


}

