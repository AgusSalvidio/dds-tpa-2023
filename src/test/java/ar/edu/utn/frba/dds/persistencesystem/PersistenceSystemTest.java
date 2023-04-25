package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.user.UserDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersistenceSystemTest {
  @Test
  @DisplayName("Create a simple PersistenceSystem with only a collection of Integers")
  public void createPersistenceSystemWithIntegersTest() {
    String integerClassName = Integer.class.getName();
    PersistenceSystem persistenceSystem = new PersistenceSystem();

    persistenceSystem.addObjectTypeToStore(integerClassName);

    persistenceSystem.storeObjectTyped(integerClassName, 1);
    persistenceSystem.storeObjectTyped(integerClassName, 4);
    persistenceSystem.storeObjectTyped(integerClassName, 15);

    Object retrievedObject = persistenceSystem.objectsFrom(integerClassName);
    List<Integer> obtainedList = (List<Integer>) retrievedObject;

    Assertions.assertTrue(obtainedList.containsAll(new ArrayList<>(Arrays.asList(1, 4, 15))));

  }

  @Test
  @DisplayName("Create a more complex PersistenceSystem with more types of objects")
  public void createPersistenceSystemWithMultipleTypeObjectsTest() {
    PersistenceSystem persistenceSystem = new PersistenceSystem();
    String integerClass = Integer.class.getName();
    String userDetailsClass = UserDetails.class.getName();
    UserDetails userDetails = new UserDetails("Basura", "Intergalactica", "basuraintergalactica@gmail.com");

    persistenceSystem.addObjectTypeToStore(integerClass);
    persistenceSystem.addObjectTypeToStore(userDetailsClass);

    persistenceSystem.storeObjectTyped(integerClass, 1);
    persistenceSystem.storeObjectTyped(integerClass, 4);
    persistenceSystem.storeObjectTyped(integerClass, 15);

    persistenceSystem.storeObjectTyped(userDetailsClass, userDetails);

    Object retrievedIntegerList = persistenceSystem.objectsFrom(integerClass);
    List<Integer> obtainedIntegerList = (List<Integer>) retrievedIntegerList;

    Object retrievedUserDetailsList = persistenceSystem.objectsFrom(userDetailsClass);
    List<UserDetails> obtainedUserDetailsList = (List<UserDetails>) retrievedUserDetailsList;

    Assertions.assertTrue(obtainedIntegerList.containsAll(new ArrayList<>(Arrays.asList(1, 4, 15))));
    Assertions.assertTrue(obtainedUserDetailsList.contains(userDetails));

  }

  @Test
  @DisplayName("When PersistenceSystem has no objects and a system looks for an specific object, should raise error")
  public void whenPersistenceSystemHasNoObjectsStoredAndAnySystemLooksForAnObjectShouldRaiseExceptionTest() {
    PersistenceSystem persistenceSystem = new PersistenceSystem();
    String integerClass = Integer.class.getName();

    persistenceSystem.addObjectTypeToStore(integerClass);

    Object retrievedIntegerList = persistenceSystem.objectsFrom(integerClass);
    List<Integer> obtainedIntegerList = (List<Integer>) retrievedIntegerList;

    Assertions.assertTrue(obtainedIntegerList.isEmpty());

    Assertions.assertThrows(RuntimeException.class, () -> persistenceSystem.findObjectTyped(integerClass, 1));

  }

  @Test
  @DisplayName("When PersistenceSystem does not have a StorageAssignment for a system and a system looks for an specific object, should raise error")
  public void whenPersistenceSystemHasNoStorageAssignmentAndAnySystemLooksForAnObjectShouldRaiseExceptionTest() {
    PersistenceSystem persistenceSystem = new PersistenceSystem();
    String integerClass = Integer.class.getName();

    Assertions.assertThrows(RuntimeException.class, () -> persistenceSystem.objectsFrom(integerClass));

  }

}
