package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.entity.EntityType;
import java.util.List;

public class EntityTypeManagementSystem extends ManagementSystem {

    public EntityTypeManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
        super(persistenceSystem);
        myTypeDescription = "A/E de Tipo de Entidades";
    }

    public EntityType entityTypeById(Integer id) {
        return this.persistenceSystem.entityTypeById(id);
    }

}
