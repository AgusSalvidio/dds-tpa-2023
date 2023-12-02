package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.location.Department;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.Map;

public class DepartmentManagementSystem extends ManagementSystem {

  public DepartmentManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    super(persistenceSystem);
    myTypeDescription = "A/E de Departamentos";
  }

  public void updateFromModel(Department object, Map<String, Object> model) {
    Department newObject = new Department();
    newObject.setName(object.getName());
    newObject.setId(object.getId());
    this.persistenceSystem.updateManaging(newObject);
  }

  public Department departmentById(Integer id) {
    return this.persistenceSystem.departmentById(id);
  }
}
