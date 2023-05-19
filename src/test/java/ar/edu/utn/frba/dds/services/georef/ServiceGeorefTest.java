package ar.edu.utn.frba.dds.services.georef;

import ar.edu.utn.frba.dds.services.georef.entities.DepartmentCollection;
import ar.edu.utn.frba.dds.services.georef.entities.MunicipalityCollection;
import ar.edu.utn.frba.dds.services.georef.entities.ProvinceCollection;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceGeorefTest {
  @Test
  @DisplayName("Get all provinces")
  public void getAllProvincesTest() throws IOException {

    ServiceGeoref georefService = ServiceGeoref.instance();
    ProvinceCollection provinceCollection = georefService.provinceCollection();

    Assertions.assertEquals(provinceCollection.quantity(), 24);
    Assertions.assertEquals(provinceCollection.start(), 0);
    Assertions.assertEquals(provinceCollection.total(), 24);
    Assertions.assertEquals(provinceCollection.provinces().size(), 24);
    Assertions.assertNull(provinceCollection.parameters().fields());

  }

  @Test
  @DisplayName("Get province filtered by name, bring only fields id and name")
  public void getSpecificProvinceTest() throws IOException {

    ServiceGeoref georefService = ServiceGeoref.instance();
    ProvinceCollection provinceCollection = georefService.provinceCollectionFilteredByName("Córdoba", "id,nombre");

    Assertions.assertEquals(provinceCollection.quantity(), 1);
    Assertions.assertEquals(provinceCollection.start(), 0);
    Assertions.assertEquals(provinceCollection.total(), 1);

    Assertions.assertEquals(provinceCollection.provinces().size(), 1);
    Assertions.assertEquals(provinceCollection.provinces().get(0).id(), "14");
    Assertions.assertEquals(provinceCollection.provinces().get(0).name(), "Córdoba");

    Assertions.assertTrue(provinceCollection.parameters().fields().contains("id"));
    Assertions.assertTrue(provinceCollection.parameters().fields().contains("nombre"));

  }

  @Test
  @DisplayName("Get all municipalities")
  public void getAllMunicipalitiesTest() throws IOException {

    ServiceGeoref georefService = ServiceGeoref.instance();
    MunicipalityCollection municipalityCollection = georefService.municipalityCollection();

    Assertions.assertEquals(municipalityCollection.quantity(), 10);
    Assertions.assertEquals(municipalityCollection.start(), 0);
    Assertions.assertEquals(municipalityCollection.total(), 1814);
    Assertions.assertEquals(municipalityCollection.municipalities().size(), 10);
    Assertions.assertNull(municipalityCollection.parameters().fields());

  }

  @Test
  @DisplayName("Get municipality filtered by province id, only bring id and name, and one result")
  public void getMunicipalityForSpecificProvinceTest() throws IOException {

    ServiceGeoref georefService = ServiceGeoref.instance();
    MunicipalityCollection municipalityCollection =
        georefService.municipalityCollectionFilteredBy("6", "id,nombre", 1);

    Assertions.assertEquals(municipalityCollection.quantity(), 1);
    Assertions.assertEquals(municipalityCollection.start(), 0);
    Assertions.assertEquals(municipalityCollection.total(), 135);

    Assertions.assertEquals(municipalityCollection.municipalities().size(), 1);
    Assertions.assertEquals(municipalityCollection.municipalities().get(0).id(), "060056");
    Assertions.assertEquals(municipalityCollection.municipalities().get(0).name(), "Bahía Blanca");

    Assertions.assertTrue(municipalityCollection.parameters().fields().contains("id"));
    Assertions.assertTrue(municipalityCollection.parameters().fields().contains("nombre"));

  }

  @Test
  @DisplayName("Get all departments")
  public void getAllDepartmentsTest() throws IOException {

    ServiceGeoref georefService = ServiceGeoref.instance();
    DepartmentCollection departmentCollection = georefService.departmentCollection();

    Assertions.assertEquals(departmentCollection.quantity(), 10);
    Assertions.assertEquals(departmentCollection.start(), 0);
    Assertions.assertEquals(departmentCollection.total(), 529);
    Assertions.assertEquals(departmentCollection.departments().size(), 10);
    Assertions.assertNull(departmentCollection.parameters().fields());

  }

  @Test
  @DisplayName("Get department filtered by province id, only bring id and name, and one result")
  public void getDepartmentForSpecificProvinceTest() throws IOException {

    ServiceGeoref georefService = ServiceGeoref.instance();
    DepartmentCollection departmentCollection =
        georefService.departmentCollectionFilteredBy("6", "id,nombre", 1);

    Assertions.assertEquals(departmentCollection.quantity(), 1);
    Assertions.assertEquals(departmentCollection.start(), 0);
    Assertions.assertEquals(departmentCollection.total(), 135);

    Assertions.assertEquals(departmentCollection.departments().size(), 1);
    Assertions.assertEquals(departmentCollection.departments().get(0).id(), "06315");
    Assertions.assertEquals(departmentCollection.departments().get(0).name(), "General Juan Madariaga");

    Assertions.assertTrue(departmentCollection.parameters().fields().contains("id"));
    Assertions.assertTrue(departmentCollection.parameters().fields().contains("nombre"));

  }

}
