package ar.edu.utn.frba.dds.addons.managementsystemaddon;

import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;

public class ManagementSystemAddOn {

  private MemoryBasedPersistenceSystem persistenceSystem() throws Exception {
    return this.relationalDatabasePersistenceSystem();
  }

  private MemoryBasedPersistenceSystem relationalDatabasePersistenceSystem() throws Exception {
    return new MemoryBasedPersistenceSystem();
  }

}

