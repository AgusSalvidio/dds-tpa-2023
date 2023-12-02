package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.location.Province;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;

import java.util.Map;

public class ProvinceManagementSystem extends ManagementSystem {

    public ProvinceManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
        super(persistenceSystem);
        myTypeDescription = "A/E de Provincias";
    }

    public void updateFromModel(Province object, Map<String, Object> model) {
        Province newObject = new Province();
        newObject.setName(object.getName());
        newObject.setId(object.getId());
        this.persistenceSystem.updateManaging(newObject);
    }

    public Province provinceById(Integer id) {
        return this.persistenceSystem.provinceById(id);
    }
}
