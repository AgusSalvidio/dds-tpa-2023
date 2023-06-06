package ar.edu.utn.frba.dds.importer;

import ar.edu.utn.frba.dds.importer.serviceholderimporter.ServiceHolderImporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceHolderImporterTest {

  private String filePath() {
    return "src/main/resources/Organismos.csv";
  }

  @Test
  @DisplayName("Import service holder records from file")
  public void importServiceHolderImportRecordFromFileTest() {
    String filePath = this.filePath();

    ServiceHolderImporter serviceHolderImporter = new ServiceHolderImporter();

    serviceHolderImporter.importFrom(filePath);

    Assertions.assertEquals(serviceHolderImporter.servicesHolderRecords().size(), 1);
    Assertions.assertEquals(serviceHolderImporter.servicesHolderRecords().get(0).name(), "BCRA");
    Assertions.assertEquals(serviceHolderImporter.servicesHolderRecords().get(0).description(), "Description");

  }

}
