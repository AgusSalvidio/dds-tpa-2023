package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import java.util.List;
import java.util.Map;

public class EstablishmentTypeManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public EstablishmentTypeManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Tipo de Establecimientos";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public void startManagingEstablishmentType(EstablishmentType type) {
    this.persistenceSystem().startManaging(type);
  }

  public void updateEstablishmentTypeWith(EstablishmentType type) {
    this.persistenceSystem().updateManaging(type);
  }

  public void stopManagingEstablishmentType(EstablishmentType type) {
    this.persistenceSystem().stopManaging(type);
  }

  public List<Object> establishmentTypes() {
    return this.persistenceSystem.objectList(EstablishmentType.class.getName());
  }

  public EstablishmentType establishmentTypeIdentifiedBy(Integer typeId) {
    return this.persistenceSystem.establishmentTypeIdentifiedBy(typeId);
  }

  public EstablishmentType establishmentTypeNamed(String typeNamed) {
    return this.persistenceSystem.establishmentTypeNamed(typeNamed);
  }

  /*
  public void updateEstablishmentTypeFrom(
      EstablishmentType establishmentTypeToUpdate,
      Map<String, Object> model) {
    String name = model.get("name").toString();

    EstablishmentType updatedEstablishmentType = new EstablishmentType();
    updatedEstablishmentType.setName(name);
    updatedEstablishmentType.setId(establishmentTypeToUpdate.getId());

    this.updateEstablishmentTypeWith(updatedEstablishmentType);
  }
  */

  public void updateEstablishmentTypeFrom(
      EstablishmentType establishmentTypeToUpdate,
      Map<String, Object> model) {
    String name = model.get("name").toString();

    establishmentTypeToUpdate.setName(name);

    this.updateEstablishmentTypeWith(establishmentTypeToUpdate);
  }

  public void startEstablishmentTypeFrom(Map<String, Object> model) {
    String name = model.get("name").toString();

    EstablishmentType establishmentType = new EstablishmentType();
    establishmentType.setName(name);

    this.startManagingEstablishmentType(establishmentType);
  }
}
