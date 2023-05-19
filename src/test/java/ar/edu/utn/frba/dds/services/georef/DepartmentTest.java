package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.Department;
import ar.edu.utn.frba.dds.services.georef.entities.Province;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DepartmentTest {

  private Province province() {
    Province province = new Province();
    province.id = "14";
    province.nombre = "Córdoba";
    return province;
  }

  @Test
  @DisplayName("Create Department")
  public void getCreateDepartmentTest() {
    Department department = new Department();
    Province province = this.province();

    department.id = "12";
    department.nombre = "Departamento A";
    department.provincia = province;

    Assertions.assertEquals(department.name(), "Departamento A");
    Assertions.assertEquals(department.id(), "12");
    Assertions.assertEquals(department.province(), province);

  }


  public String id;
  public String nombre;

  public Province provincia;
}
