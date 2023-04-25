package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;

public interface ManagementSystem {
  private PersistenceSystem persistenceSystem() throws Exception {
    throw new Exception("Subclass responsability");
  }

  public String typeDescription();

  public void startManaging(Object anObject);

  public void stopManaging(Object anObject);

  public void updateWith(Object currentObject, Object updatedObject);
}
