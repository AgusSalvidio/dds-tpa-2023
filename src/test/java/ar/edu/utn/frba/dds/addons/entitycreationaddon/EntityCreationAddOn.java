package ar.edu.utn.frba.dds.addons.entitycreationaddon;

import ar.edu.utn.frba.dds.addons.incidentcreationaddon.IncidentCreationAddOn;
import ar.edu.utn.frba.dds.entity.TransportLine;

public class EntityCreationAddOn {

  public TransportLine entityA() throws Exception {
    TransportLine transportLine = new TransportLine();

    transportLine.addNewIncident(new IncidentCreationAddOn().notWorkingElevatorIncident());
    transportLine.addNewIncident(new IncidentCreationAddOn().notWorkingEscalatorIncident());

    return transportLine;
  }

  public TransportLine entityB() throws Exception {
    TransportLine transportLine = new TransportLine();

    transportLine.addNewIncident(new IncidentCreationAddOn().notWorkingElevatorIncident());

    return transportLine;
  }

  public TransportLine entityC() throws Exception {
    TransportLine transportLine = new TransportLine();

    transportLine.addNewIncident(new IncidentCreationAddOn().notWorkingElevatorIncident());
    transportLine.addNewIncident(new IncidentCreationAddOn().workingElevatorIncident());

    return transportLine;
  }
}
