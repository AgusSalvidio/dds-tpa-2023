package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class LocationManagementSystem extends ManagementSystem {

  public LocationManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Direcciones";
  }

  public void updateFromModel(Location object, Map<String, Object> model) {
    Location newObject = new Location();
    newObject.setStreet(object.getStreet());
    newObject.setNumber(object.getNumber());
    newObject.setId(object.getId());
    this.persistenceSystem.updateManaging(newObject);
  }

  public Location locationById(Integer id) {
    return this.persistenceSystem.locationById(id);
  }

  public List<Object> locations() {
    return this.persistenceSystem.objectList(Location.class.getName());
  }
}


