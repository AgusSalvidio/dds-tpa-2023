package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class EntityNameManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public EntityNameManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Nombre de Entidades";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public void startManagingEntityName(EntityName name) {
    this.persistenceSystem().startManaging(name);
  }

  public void updateEntityNameWith(EntityName name) {
    this.persistenceSystem().updateManaging(name);
  }

  public void stopManagingEntityName(EntityName name) {
    this.persistenceSystem().stopManaging(name);
  }

  public List<Object> entityNames() {
    return this.persistenceSystem.objectList(EntityName.class.getName());
  }

  public EntityName entityNameIdentifiedBy(Integer nameId) {
    return this.persistenceSystem.entityNameIdentifiedBy(nameId);
  }

  public EntityName entityNameNamed(String nameNamed) {
    return this.persistenceSystem.entityNameNamed(nameNamed);
  }

  /*
  public void updateEntityNameFrom(
      EntityName entityNameToUpdate,
      Map<String, Object> model) {
    String name = model.get("name").toString();

    EntityName updatedEntityName = new EntityName();
    updatedEntityName.setName(name);
    updatedEntityName.setId(entityNameToUpdate.getId());

    this.updateEntityNameWith(updatedEntityName);
  }
  */

  public void updateEntityNameFrom(
      EntityName entityNameToUpdate,
      Map<String, Object> model) {
    String name = model.get("name").toString();

    entityNameToUpdate.setName(name);

    this.updateEntityNameWith(entityNameToUpdate);
  }

  public void startEntityNameFrom(Map<String, Object> model) {
    String name = model.get("name").toString();

    EntityName entityName = new EntityName();
    entityName.setName(name);

    this.startManagingEntityName(entityName);
  }
}
