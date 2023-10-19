package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class EntityManagementSystem {
  MemoryBasedPersistenceSystem persistenceSystem;

  public EntityManagementSystem(MemoryBasedPersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescripction() {
    return "Sistema de administración de entidades.";
  }

  private MemoryBasedPersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public static EntityManagementSystem workingWith(
      MemoryBasedPersistenceSystem persistenceSystem
  ) {
    return new EntityManagementSystem(persistenceSystem);
  }

  public void startManagingTransportLine(TransportLine transportLine) {
    this.persistenceSystem().startManagingTransportLine(transportLine);
  }

  public void stopManagingTransportLine(TransportLine transportLine) {
    this.persistenceSystem().stopManagingTransportLine(transportLine);
  }

  public List<Entity> entities() {
    return this.persistenceSystem().entities();
  }

  public void updateWith(Entity currentEntity, Entity updateEntity) {
    currentEntity.synchronizedWith(updateEntity);
  }

  public void startManagingEntityForm(Map model) {
    Establishment departure = (Establishment) model.get("departure");
    Establishment arrival = (Establishment) model.get("arrival");
    Direction direction = (Direction) model.get("direction");

    this.startManagingTransportLine(TransportLine.composedOf(departure, arrival, direction));
  }
}
