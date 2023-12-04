package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.community.MemberRole;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
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

  public void startManagingCommunity(Community community) {
    this.persistenceSystem().startManaging(community);
  }

  public void updateCommunityWith(Community community) {
    this.persistenceSystem().updateManaging(community);
  }

  public void stopManagingCommunity(Community community) {
    this.persistenceSystem().stopManaging(community);
  }

  public List<Object> communities() {
    return this.persistenceSystem.objectList(Community.class.getName());
  }

  public Community communityIdentifiedBy(Integer communityId) {
    return this.persistenceSystem.communityIdentifiedBy(communityId);
  }

  public Community communityNamed(String communityNamed) {
    return this.persistenceSystem.communityNamed(communityNamed);
  }
  public List<Object> communitiesForUser(User anUser) {
    return this.persistenceSystem.objectList(Community.class.getName());
  }

  /*
  public void updateCommunityFrom(Community communityToUpdate, Map<String, Object> model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();

    Community updatedCommunity = Community.composedOf(name, description);
    updatedCommunity.setId(communityToUpdate.getId());

    this.updateCommunityWith(updatedCommunity);
  }
   */

  public void updateCommunityFrom(Community communityToUpdate, Map<String, Object> model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();

    communityToUpdate.setName(name);
    communityToUpdate.setDescription(description);

    this.updateCommunityWith(communityToUpdate);
  }

  public void startManagingCommunityForm(Map<String, Object> model) {
    String name = model.get("name").toString();
    String description = model.get("description").toString();

    this.startManagingCommunity(Community.composedOf(name, description));
  }

  public void updateCommunityMemberFrom(Community communityToUpdate, Map<String, Object> model) {
    Integer userId = Integer.valueOf(model.get("user").toString());
    User user = this.persistenceSystem.userIdentifiedBy(userId);
    MemberRole role = MemberRole.valueOf(model.get("role").toString());
    communityToUpdate.addMemberComposedOf(user, role);
    this.updateCommunityWith(communityToUpdate);
  }

}
  /*
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
  */


