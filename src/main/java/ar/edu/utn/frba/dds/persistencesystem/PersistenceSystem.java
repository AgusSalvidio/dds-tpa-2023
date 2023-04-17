package ar.edu.utn.frba.dds.persistencesystem;

import java.util.HashMap;
import java.util.List;

/*
  This implementation is not the best especially with all the casting made to store objects.
  However, knowing that Database persistence its on the way, we can bite the bullet with this.
  Or like Hernan W. said, if you think it can be improved, then do it yourself.*/
public class PersistenceSystem {
  HashMap memoryStorage;

  public PersistenceSystem() {
    this.memoryStorage = new HashMap();
  }

  public void addObjectTypeToStore(String anObjectClassName, List<Object> anObjectList) {
    this.memoryStorage.put(anObjectClassName, anObjectList);
  }

  /**
   * Storing different typed objects.
   */
  public void storeObjectTyped(String anObjectClassName, Object anObject) {
    List<Object> obtainedList = (List<Object>) this.memoryStorage.get(anObjectClassName);
    obtainedList.add(anObject);
    this.memoryStorage.put(anObjectClassName, obtainedList);
  }

  public List<Object> objectsFrom(String anObjectClassName) {
    return (List<Object>) this.memoryStorage.get(anObjectClassName);
  }

}
