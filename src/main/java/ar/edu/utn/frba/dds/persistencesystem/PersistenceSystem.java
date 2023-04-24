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

  /**
   * Storing different typed objects.
   */
  public void storeObjectTyped(String anObjectClassName, Object anObject) {
    StorageAssignment selectedStorageAssignment = this.storageAssignmentFor(anObjectClassName);
    selectedStorageAssignment.store(anObject);
  }

  public void removeObjectTyped(String anObjectClassName, Object anObject) {
    StorageAssignment storageAssignment = this.storageAssignmentFor(anObjectClassName);
    storageAssignment.remove(anObject);

  }

  public Object findObjectTyped(String anObjectClassName, Object anObject) {
    List<Object> obtainedObject = this.objectsFrom(anObjectClassName);
    return obtainedObject.get(obtainedObject.indexOf(anObject));

  }


  /**
   * This is an object created to comply with the CodeSmells check.
   * Why is so irritating?
   */
  public StorageAssignment storageAssignmentFor(String anObjectClassName) {
    List<Object> obtainedStorageAssignmentList =
        this.memoryStorage.stream()
            .filter(storageAssignment -> storageAssignment.className()
                .equals(anObjectClassName))
            .collect(Collectors.toList());
    return (StorageAssignment) obtainedStorageAssignmentList.get(0);
  }

  public List<Object> objectsFrom(String anObjectClassName) {
    return storageAssignmentFor(anObjectClassName).objectList();
  }

}
