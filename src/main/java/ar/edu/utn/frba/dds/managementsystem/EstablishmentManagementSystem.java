package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.persistencesystem.RelationalDatabasePersistenceSystem;
import ar.edu.utn.frba.dds.service.Service;

import java.util.List;
import java.util.Map;

public class EstablishmentManagementSystem {
  RelationalDatabasePersistenceSystem persistenceSystem;

  public EstablishmentManagementSystem(RelationalDatabasePersistenceSystem persistenceSystem) {
    this.persistenceSystem = persistenceSystem;
  }

  public String typeDescription() {
    return "Sistema de Administración de Establecimientos";
  }

  private RelationalDatabasePersistenceSystem persistenceSystem() {
    return this.persistenceSystem;
  }

  public void startManagingEstablishment(Establishment anEstablishment) {
    this.persistenceSystem().startManaging(anEstablishment);
  }

  public void updateEstablishmentWith(Establishment anEstablishment) {
    this.persistenceSystem().updateManaging(anEstablishment);
  }

  public void stopManagingEstablishment(Establishment anEstablishment) {
    this.persistenceSystem().stopManaging(anEstablishment);
  }

  public List<Object> establishments() {
    return this.persistenceSystem.objectList(Establishment.class.getName());
  }

  public List<Service> establishmentsServicesNotById(Integer Id) {
    return this.persistenceSystem.establishmentsServicesNotById(Id);
  }

  public Establishment establishmentIdentifiedBy(Integer anEstablishmentId) {
    return this.persistenceSystem.establishmentIdentifiedBy(anEstablishmentId);
  }

  public Establishment establishmentNamed(String anEstablishmentName) {
    return this.persistenceSystem.establishmentNamed(anEstablishmentName);
  }

  public void updateEstablishmentFrom(Establishment establishmentToUpdate, Map model) {
    String name = model.get("name").toString();
    Integer establishmentTypeId = Integer.valueOf(model.get("type").toString());

    EstablishmentType establishmentType =
        this.persistenceSystem.establishmentTypeIdentifiedBy(establishmentTypeId);

    Establishment updatedEstablishment =
        Establishment.composedOf(establishmentType, name, null);
    updatedEstablishment.setId(establishmentToUpdate.getId());

    this.updateEstablishmentWith(updatedEstablishment);
  }

  public void startManagingEstablishmentFrom(Map model) {
    String name = model.get("name").toString();
    Integer establishmentTypeId = Integer.valueOf(model.get("type").toString());

    EstablishmentType establishmentType =
        this.persistenceSystem.establishmentTypeIdentifiedBy(establishmentTypeId);

    this.startManagingEstablishment(
        Establishment.composedOf(establishmentType, name, null));
  }

}
