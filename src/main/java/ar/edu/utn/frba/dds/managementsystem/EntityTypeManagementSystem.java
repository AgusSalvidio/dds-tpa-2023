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

    public String typeDescripction() {
        return "Sistema de administración de entidades.";
    }

    private RelationalDatabasePersistenceSystem persistenceSystem() {
        return this.persistenceSystem;
    }

    public static EntityTypeManagementSystem workingWith(
            RelationalDatabasePersistenceSystem persistenceSystem
    ) {
        return new EntityTypeManagementSystem(persistenceSystem);
    }

    public void startManagingEntityType(EntityType entityType) {
        this.persistenceSystem().startManagingEntityType(entityType);
    }

    public void stopManagingEntityType(EntityType entityType) {
        this.persistenceSystem().stopManagingEntityType(entityType);
    }

    public List<EntityType> entityTypes() {
        return this.persistenceSystem().entityTypes();
    }

    public void updateWith(EntityType currentEntity, EntityType updateEntity) {
        //currentEntity.synchronizedWith(updateEntity);
    }

}
