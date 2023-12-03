package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class EntityTypeManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public EntityTypeManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Tipo de Entidades";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public void startManagingEntityType(EntityType type) {
    this.persistenceSystem().startManaging(type);
  }

  public void updateEntityTypeWith(EntityType type) {
    this.persistenceSystem().updateManaging(type);
  }

  public void stopManagingEntityType(EntityType type) {
    this.persistenceSystem().stopManaging(type);
  }

  public List<Object> entityTypes() {
    return this.persistenceSystem.objectList(EntityType.class.getName());
  }

  public EntityType entityTypeIdentifiedBy(Integer typeId) {
    return this.persistenceSystem.entityTypeIdentifiedBy(typeId);
  }

  public EntityType entityTypeNamed(String typeNamed) {
    return this.persistenceSystem.entityTypeNamed(typeNamed);
  }

  /*
  public void updateEntityTypeFrom(
      EntityType entityTypeToUpdate,
      Map<String, Object> model) {
    String name = model.get("name").toString();

    EntityType updatedEntityType = new EntityType();
    updatedEntityType.setName(name);
    updatedEntityType.setId(entityTypeToUpdate.getId());

    this.updateEntityTypeWith(updatedEntityType);
  }
  */

  public void updateEntityTypeFrom(
      EntityType entityTypeToUpdate,
      Map<String, Object> model) {
    String name = model.get("name").toString();

    entityTypeToUpdate.setName(name);

    this.updateEntityTypeWith(entityTypeToUpdate);
  }

  public void startEntityTypeFrom(Map<String, Object> model) {
    String name = model.get("name").toString();

    EntityType entityType = new EntityType();
    entityType.setName(name);

    this.startManagingEntityType(entityType);
  }
}
