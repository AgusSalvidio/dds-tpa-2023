package ar.edu.utn.frba.dds.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceTest {
  @Test
  @DisplayName("Create a service")
  public void createAServiceTest() {

    Service service = new Service("Transporte");

    Assertions.assertEquals(service.name(), "Transporte");

  }

}
