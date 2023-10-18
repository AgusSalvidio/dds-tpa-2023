package ar.edu.utn.frba.dds.addons.ownapiservicecreationaddon;

import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.BodyData;

public class BodyDataCreationAddOn {
  public BodyData bodyData() {
    BodyData bodyData = new BodyData();
    bodyData.setCfn(3.14);
    bodyData.addNewEntityData(new EntityDataCreationAddOn().entityDataA());
    bodyData.addNewEntityData(new EntityDataCreationAddOn().entityDataB());
    bodyData.addNewEntityData(new EntityDataCreationAddOn().entityDataC());
    return bodyData;
  }
}
