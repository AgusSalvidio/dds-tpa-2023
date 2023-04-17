package ar.edu.utn.frba.dds.persistencesystem;

import ar.edu.utn.frba.dds.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersistenceSystemTest {
  @Test
  @DisplayName("Create a simple PersistenceSystem with only a collection of Integers")
  public void createPersistenceSystemWithIntegersTest() {
    PersistenceSystem persistenceSystem = new PersistenceSystem();
    List<Object> integerList = new ArrayList<>();
    integerList.add(1);
    integerList.add(2);
    integerList.add(4);
    persistenceSystem.addObjectTypeToStore(Integer.class.getName(), integerList);
    Object retrievedObject = persistenceSystem.objectsFrom(Integer.class.getName());
    List<Integer> obtainedList = (List<Integer>) retrievedObject;

    Assertions.assertEquals(integerList, obtainedList);

  }

  @Test
  @DisplayName("Create a more complex PersistenceSystem with more types of objects")
  public void createPersistenceSystemWithMultipleTypeObjectsTest() {
    PersistenceSystem persistenceSystem = new PersistenceSystem();
    String integerClass = Integer.class.getName();
    String userDetailsClass = UserDetails.class.getName();
    List<Object> integerList = new ArrayList<>();
    integerList.add(1);
    integerList.add(2);
    integerList.add(4);

    List<Object> userDetailsList = new ArrayList<>();
    userDetailsList.add(new UserDetails("Juan", "Rodriguez", "juanrodriguez@gmail.com"));
    userDetailsList.add(new UserDetails("Pepe", "Sand", "pepesand@gmail.com"));
    userDetailsList.add(new UserDetails("Basura", "Intergalactica", "basuraintergalactica@gmail.com"));

    persistenceSystem.addObjectTypeToStore(integerClass, integerList);
    persistenceSystem.addObjectTypeToStore(userDetailsClass, userDetailsList);

    Object retrievedIntegerList = persistenceSystem.objectsFrom(integerClass);
    List<Integer> obtainedIntegerList = (List<Integer>) retrievedIntegerList;

    Object retrievedUserDetailsList = persistenceSystem.objectsFrom(userDetailsClass);
    List<UserDetails> obtainedUserDetailsList = (List<UserDetails>) retrievedUserDetailsList;

    Assertions.assertEquals(integerList, obtainedIntegerList);
    Assertions.assertEquals(userDetailsList, obtainedUserDetailsList);

  }

}
