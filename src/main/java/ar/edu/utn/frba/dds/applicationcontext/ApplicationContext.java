package ar.edu.utn.frba.dds.applicationcontext;

import ar.edu.utn.frba.dds.managementsystem.*;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;

public class ApplicationContext {

  RelationalDatabasePersistenceSystem persistenceSystem = new RelationalDatabasePersistenceSystem();

  public ApplicationContext() throws Exception {
  }

  public UserManagementSystem userManagementSystem() {
    return new UserManagementSystem(this.persistenceSystem);
  }

  public User userIdentifiedBy(Integer anUserId) {
    return this.userManagementSystem().userIdentifiedBy(anUserId);
  }

  public User userNamed(String anUserName) {
    return this.userManagementSystem().userNamed(anUserName);
  }

  public User loggedUser(Context context) {
    if (context.sessionAttribute("user_id") == null) {
      return null;
    } else {
      Integer userId = context.sessionAttribute("user_id");
      return this.userManagementSystem().userIdentifiedBy(userId);
    }
  }
  public ServiceManagementSystem serviceManagementSystem() {
    return new ServiceManagementSystem(this.persistenceSystem);
  }

  public EstablishmentTypeManagementSystem establishmentTypeManagementSystem() {
    return new EstablishmentTypeManagementSystem(this.persistenceSystem);
  }

  public EstablishmentManagementSystem establishmentManagementSystem() {
    return new EstablishmentManagementSystem(this.persistenceSystem);
  }

  public EntityTypeManagementSystem entityTypeManagementSystem() {
    return new EntityTypeManagementSystem(this.persistenceSystem);
  }

  public EntityNameManagementSystem entityNameManagementSystem() {
    return new EntityNameManagementSystem(this.persistenceSystem);
  }
  public EntityManagementSystem entityManagementSystem() {
    return new EntityManagementSystem(this.persistenceSystem);
  }







  public IncidentManagementSystem incidentManagementSystem() {
    return new IncidentManagementSystem(this.persistenceSystem);
  }

  public CommunityManagementSystem communityManagementSystem() {
    return new CommunityManagementSystem(this.persistenceSystem);
  }

  public IncidentPerCommunityManagementSystem incidentPerCommunityManagementSystem() {
    return new IncidentPerCommunityManagementSystem(this.persistenceSystem);
  }

  public ServiceHolderManagementSystem serviceHolderManagementSystem() {
    return new ServiceHolderManagementSystem(this.persistenceSystem);
  }
  /*
  AGREGADO POR FDM 01-12-2023
  */
  public ProvinceManagementSystem provinceManagementSystem() {
    return new ProvinceManagementSystem(this.persistenceSystem);
  }

  public DepartmentManagementSystem departmentManagementSystem() {
    return new DepartmentManagementSystem(this.persistenceSystem);
  }

  public MunicipalityManagementSystem municipalityManagementSystem() {
    return new MunicipalityManagementSystem(this.persistenceSystem);
  }

  public LocationManagementSystem locationManagementSystem() {
    return new LocationManagementSystem(this.persistenceSystem);
  }
}
