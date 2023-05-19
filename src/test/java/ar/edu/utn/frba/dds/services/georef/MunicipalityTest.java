package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.Municipality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MunicipalityTest {

  @Test
  @DisplayName("Create Municipality")
  public void getCreateMunicipalityTest() {

    Municipality municipality = new Municipality();
    municipality.id = "10";
    municipality.nombre = "Municipio A";

    Assertions.assertEquals(municipality.name(), "Municipio A");
    Assertions.assertEquals(municipality.id(), "10");

  }
}
