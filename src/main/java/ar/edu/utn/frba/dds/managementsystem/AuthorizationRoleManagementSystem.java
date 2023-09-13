package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.eventnotificationsystem.notifiableevent.NotifiableEvent;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import java.util.List;
import java.util.Map;

public class AuthorizationRoleManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public AuthorizationRoleManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Roles de Autorización";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static AuthorizationRoleManagementSystem workingWith(
      RelationalDatabasePersistenceSystem persistenceSystem) {
    return new AuthorizationRoleManagementSystem(persistenceSystem);
  }

  public void startManaging(AuthorizationRole authorizationRole) {
    this.persistenceSystem().startManagingAuthorizationRole(authorizationRole);
  }

  public List<AuthorizationRole> roles() {
    return this.persistenceSystem.roles();
  }

  public void stopManaging(AuthorizationRole authorizationRole) {
    this.persistenceSystem().stopManagingAuthorizationRole(authorizationRole);
  }

  public void updateWith(
      AuthorizationRole authorizationRole,
      AuthorizationRole updatedAuthorizationRole) {
    authorizationRole.synchronizeWith(updatedAuthorizationRole);
  }

  public void receiveFrom(NotifiableEvent event, Object publisher) {
    /* For now, this system should have an implementation. This will be enhanced
     when the extracting the implementation from ManagementSystem -asalvidio*/
  }

  public void startManagingAuthorizationRoleFrom(Map model) {

    String role = model.get("role").toString();
    User user = (User) model.get("users");
    this.startManaging(AuthorizationRole.composedOf(user, role));

  }

}
