package ar.edu.utn.frba.dds.addons.ownapiservicecreationaddon;

import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.EntityData;

public class EntityDataCreationAddOn {
  public EntityData entityDataA() {
    EntityData entityData = new EntityData();
    entityData.setName("entityA");
    entityData.setIncidentsNotResolved(10);
    entityData.setMembersAffected(123);
    entityData.addNewIncidentDuration(1.0);
    entityData.addNewIncidentDuration(2.0);
    entityData.addNewIncidentDuration(3.0);
    return entityData;
  }

  public EntityData entityDataB() {
    EntityData entityData = new EntityData();
    entityData.setName("entityB");
    entityData.setIncidentsNotResolved(5);
    entityData.setMembersAffected(321);
    entityData.addNewIncidentDuration(4.5);
    entityData.addNewIncidentDuration(5.5);
    return entityData;
  }

  public EntityData entityDataC() {
    EntityData entityData = new EntityData();
    entityData.setName("entityC");
    entityData.setIncidentsNotResolved(15);
    entityData.setMembersAffected(50);
    entityData.addNewIncidentDuration(10.0);
    entityData.addNewIncidentDuration(8.0);
    return entityData;
  }
}
