package ar.edu.utn.frba.dds.addons.servicescreationaddon;

import ar.edu.utn.frba.dds.service.State;

public class StateCreationAddOn {

  public State inServiceState() {
    return State.composedOf("IN_SERVICE", "Normally working Service");
  }

  public State notInServiceState() {
    return State.composedOf("NOT_IN_SERVICE", "Not working Service");
  }

  public State openIncident() {
    return State.composedOf("OPEN", "Open Incident");
  }

  public State closedIncident() {
    return State.composedOf("CLOSED", "Closed Incident");
  }

}
