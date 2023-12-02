package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.Organization;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.Escalator;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.service.Toilet;
import java.util.List;
import java.util.Map;

public class EntityManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public EntityManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Entidades";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public void startManagingOrganization(Organization anOrganization) {
    this.persistenceSystem().startManaging(anOrganization);
  }

  public void updateOrganizationWith(Organization anOrganization) {
    this.persistenceSystem().updateManaging(anOrganization);
  }

  public void startManagingTransportLine(TransportLine transportLine) {
    this.persistenceSystem().startManaging(transportLine);
  }

  public void updateTransportLineWith(TransportLine transportLine) {
    this.persistenceSystem().updateManaging(transportLine);
  }

  public void stopManagingEntity(Entity anEntity) {
    this.persistenceSystem().stopManaging(anEntity);
  }

  public void updateEntityWith(Entity anEntity) {
    this.persistenceSystem().updateManaging(anEntity);
  }

  public List<Object> entities() {
    return this.persistenceSystem.objectList(Entity.class.getName());
  }

  public Entity entityIdentifiedBy(Integer entityId) {
    return this.persistenceSystem.entityIdentifiedBy(entityId);
  }

  public Entity entityNamed(String entityName) {
    return this.persistenceSystem.entityNamed(entityName);
  }

  public void updateEntityFrom(Entity entityToUpdate, Map model) {
    Integer nameId = Integer.valueOf(model.get("type").toString());
    EntityName name = this.persistenceSystem.entityNameIdentifiedBy(nameId);
    Integer typeId = Integer.valueOf(model.get("type").toString());
    EntityType type = this.persistenceSystem.entityTypeIdentifiedBy(typeId);

    String entity = entityToUpdate.getClass().getSimpleName();

    switch (entity) {
      case "Organization" -> {
        Organization updatedOrganization = new Organization();
        updatedOrganization.setName(name);
        updatedOrganization.setType(type);
        updatedOrganization.setId(updatedOrganization.getId());
        this.updateEntityWith(updatedOrganization);
      }
      case "TransportLine" -> {
        TransportLine updatedTransportLine =
            TransportLine.composedOf(null, null, Direction.RETURN);
        updatedTransportLine.setName(name);
        updatedTransportLine.setType(type);
        this.updateEntityWith(updatedTransportLine);
      }
      default -> {
      }
    }
  }

  public void startManagingEntityFrom(Map model) {
    Integer nameId = Integer.valueOf(model.get("type").toString());
    EntityName name = this.persistenceSystem.entityNameIdentifiedBy(nameId);
    Integer typeId = Integer.valueOf(model.get("type").toString());
    EntityType type = this.persistenceSystem.entityTypeIdentifiedBy(typeId);
    String entity = model.get("entity").toString();

    switch (entity) {
      case "organization" -> {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setType(type);
        this.startManagingOrganization(organization);
      }
      case "transportLine" -> {
        TransportLine transportLine =
            TransportLine.composedOf(null, null, Direction.RETURN);
        transportLine.setName(name);
        transportLine.setType(type);
        this.startManagingTransportLine(transportLine);
      }
      default -> {
      }
    }
  }
}
