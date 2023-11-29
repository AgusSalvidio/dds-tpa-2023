package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

public class OrganizationManagementSystem extends ManagementSystem {
    public OrganizationManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
        super(persistenceSystem);
        myTypeDescription = "A/E de Organizaciones";
    }
    public Entity entityById(Integer id) {
        return this.persistenceSystem.entityById(id);
    }

}
