package ar.edu.utn.frba.dds.persistencesystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
  This implementation is not the best especially with all the casting made to store objects.
  However, knowing that Database persistence its on the way, we can bite the bullet with this.
  Or like Hernan W. said, if you think it can be improved, then do it yourself.*/
public class PersistenceSystem {
  List<StorageAssignment> memoryStorage = new ArrayList<>();

  public void addObjectTypeToStore(String anObjectClassName) {
    this.memoryStorage.add(new StorageAssignment(anObjectClassName));
  }

  public void storeObjectTyped(String anObjectClassName, Object anObject) {
    StorageAssignment selectedStorageAssignment = this.storageAssignmentFor(anObjectClassName);
    selectedStorageAssignment.store(anObject);
  }

  public void removeObjectTyped(String anObjectClassName, Object anObject) {
    StorageAssignment storageAssignment = this.storageAssignmentFor(anObjectClassName);
    storageAssignment.remove(anObject);

  }

  public Object findObjectTyped(String anObjectClassName, Object anObject) {
    try {
      List<Object> obtainedObjects = this.objectsFrom(anObjectClassName);
      return obtainedObjects.get(obtainedObjects.indexOf(anObject));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

  }

  public StorageAssignment storageAssignmentFor(String anObjectClassName) {
    try {
      List<Object> obtainedStorageAssignmentList =
          this.memoryStorage.stream()
              .filter(storageAssignment -> storageAssignment.className()
                  .equals(anObjectClassName))
              .collect(Collectors.toList());
      return (StorageAssignment) obtainedStorageAssignmentList.get(0);
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  public List<Object> objectsFrom(String anObjectClassName) {
    return storageAssignmentFor(anObjectClassName).objectList();
  }

}
