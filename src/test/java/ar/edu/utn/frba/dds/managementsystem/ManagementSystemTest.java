package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.persistencesystem.MemoryBasedPersistenceSystem;
import ar.edu.utn.frba.dds.persistencesystem.PersistenceSystem;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ManagementSystemTest {
  private PersistenceSystem persistenceSystem() {
    return this.memoryBasedPersistenceSystem();
  }

  private MemoryBasedPersistenceSystem memoryBasedPersistenceSystem() {
    return new MemoryBasedPersistenceSystem();
  }

  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");
  }

  @Test
  @DisplayName("Create an object management system")
  public void createAnObjectManagementSystemTest() throws Exception {
    UserManagementSystem userSystem = UserManagementSystem.workingWith(this.persistenceSystem());

    Assertions.assertEquals(userSystem.typeDescription(), "Sistema de Administración de Usuarios");

  }

}
