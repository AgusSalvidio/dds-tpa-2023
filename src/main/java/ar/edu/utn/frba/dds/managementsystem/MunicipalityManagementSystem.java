package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.location.Municipality;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.Map;

public class MunicipalityManagementSystem extends ManagementSystem {

  public MunicipalityManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Municipios";
  }

  public void updateFromModel(Municipality object, Map<String, Object> model) {
    Municipality newObject = new Municipality();
    newObject.setName(object.getName());
    newObject.setId(object.getId());
    this.persistenceSystem.updateManaging(newObject);
  }

  public Municipality municipalityById(Integer id) {
    return this.persistenceSystem.municipalityById(id);
  }
}

