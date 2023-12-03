package ar.edu.utn.frba.dds.managementsystem;

import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.establishment.EstablishmentType;
import ar.edu.utn.frba.dds.location.Location;
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

  public List<Service> establishmentsServicesNotById(Integer id) {
    return this.persistenceSystem.establishmentsServicesNotById(id);
  }

  public Establishment establishmentIdentifiedBy(Integer anEstablishmentId) {
    return this.persistenceSystem.establishmentIdentifiedBy(anEstablishmentId);
  }

  public Establishment establishmentNamed(String anEstablishmentName) {
    return this.persistenceSystem.establishmentNamed(anEstablishmentName);
  }

  /*
  public void updateEstablishmentFrom(Establishment establishmentToUpdate, Map model) {
    String name = model.get("name").toString();
    Integer establishmentTypeId = Integer.valueOf(model.get("type").toString());
    EstablishmentType establishmentType =
        this.persistenceSystem.establishmentTypeIdentifiedBy(establishmentTypeId);
    Integer locationId = Integer.valueOf(model.get("location").toString());
    Location location = this.persistenceSystem.locationIdentifiedBy(locationId);

    Establishment updatedEstablishment =
        Establishment.composedOf(establishmentType, name, location);
    updatedEstablishment.setId(establishmentToUpdate.getId());

    this.updateEstablishmentWith(updatedEstablishment);
  }
  */

  public void updateEstablishmentFrom(Establishment establishmentToUpdate, Map model) {
    String name = model.get("name").toString();
    Integer establishmentTypeId = Integer.valueOf(model.get("type").toString());
    EstablishmentType establishmentType =
        this.persistenceSystem.establishmentTypeIdentifiedBy(establishmentTypeId);
    Integer locationId = Integer.valueOf(model.get("location").toString());
    Location location = this.persistenceSystem.locationIdentifiedBy(locationId);

    establishmentToUpdate.setType(establishmentType);
    establishmentToUpdate.setName(name);
    establishmentToUpdate.setLocation(location);

    this.updateEstablishmentWith(establishmentToUpdate);
  }

  public void startManagingEstablishmentFrom(Map model) {
    String name = model.get("name").toString();
    Integer establishmentTypeId = Integer.valueOf(model.get("type").toString());
    EstablishmentType establishmentType =
        this.persistenceSystem.establishmentTypeIdentifiedBy(establishmentTypeId);
    Integer locationId = Integer.valueOf(model.get("location").toString());
    Location location = this.persistenceSystem.locationIdentifiedBy(locationId);

    this.startManagingEstablishment(
        Establishment.composedOf(establishmentType, name, location));
  }

  public void updateEstablishmentServiceFrom(Establishment establishmentToUpdate, Map model) {
    Integer serviceId = Integer.valueOf(model.get("service").toString());
    Service service = this.persistenceSystem.serviceIdentifiedBy(serviceId);
    establishmentToUpdate.addNewService(service);
    this.updateEstablishmentWith(establishmentToUpdate);
  }

}
