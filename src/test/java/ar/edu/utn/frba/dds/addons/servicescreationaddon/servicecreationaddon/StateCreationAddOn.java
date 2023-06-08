package ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon;

import ar.edu.utn.frba.dds.service.State;

public class StateCreationAddOn {

  public State inServiceState() {
    State state = new State();
    state.setName("IN_SERVICE");
    return state;
  }

  public State notInServiceState() {
    State state = new State();
    state.setName("NOT_IN_SERVICE");
    return state;
  }

}
