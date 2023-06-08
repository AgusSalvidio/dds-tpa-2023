package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.Province;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProvinceTest {
  @Test
  @DisplayName("Create Province")
  public void getCreateProvinceTest() {

    Province province = new Province();
    province.id = "14";
    province.nombre = "Córdoba";

    Assertions.assertEquals(province.name(), "Córdoba");
    Assertions.assertEquals(province.id(), "14");

  }
}
