package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class EntityManagementSystem extends ManagementSystem {

    public EntityManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Entidades";
  }

  public Entity entityById(Integer id) {
    return this.persistenceSystem.entityById(id);
  }

}
