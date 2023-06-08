package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.SectionCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.StateCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
  private Elevator elevator() {
    return new ElevatorCreationAddOn().elevator();
  }

  private Section sectionA() {
    return new SectionCreationAddOn().sectionA();
  }

  private Section sectionB() {
    return new SectionCreationAddOn().sectionB();
  }

  private State stateA() {
    return new StateCreationAddOn().inServiceState();
  }

  @Test
  @DisplayName("Elevator has a lot of sections")
  public void elevatorHasALotOfSectionsTest() {

    Elevator elevator = this.elevator();
    elevator.addNewSection(this.sectionA());
    elevator.addNewSection(this.sectionB());

    Assertions.assertEquals(2, elevator.getSections().size());
  }

  @Test
  @DisplayName("Elevator is in service")
  public void elevatorIsInServiceTest() {
    Elevator elevator = this.elevator();
    elevator.setState(this.stateA());

    Assertions.assertEquals("IN_SERVICE", elevator.getState().getName());
  }

}
