package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.EscalatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.SectionCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ToiletCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceGroupTest {
  private ServiceGroup serviceGroup() {
    return new ServiceGroup();
  }

  private Escalator escalator() {
    Escalator escalator = new EscalatorCreationAddOn().escalatorA();
    escalator.addNewSection(new SectionCreationAddOn().sectionA());
    escalator.addNewSection(new SectionCreationAddOn().sectionB());
    return escalator;
  }

  private Toilet toilet() {
    Toilet toilet = new ToiletCreationAddOn().toiletA();
    toilet.addNewSection(new SectionCreationAddOn().sectionA());
    toilet.addNewSection(new SectionCreationAddOn().sectionB());
    return toilet;
  }

  private Elevator elevator() {
    Elevator elevator = new ElevatorCreationAddOn().elevator();
    elevator.addNewSection(new SectionCreationAddOn().sectionA());
    elevator.addNewSection(new SectionCreationAddOn().sectionB());
    return elevator;
  }

  @Test
  @DisplayName("Group of different services")
  public void groupOfDifferentServicesTest() {

    Elevator elevator = this.elevator();
    Escalator escalator = this.escalator();
    Toilet toilet = this.toilet();
    ServiceGroup serviceGroup = this.serviceGroup();

    serviceGroup.addNewService(elevator);
    serviceGroup.addNewService(escalator);
    serviceGroup.addNewService(toilet);

    Assertions.assertEquals(3, serviceGroup.getServices().size());
  }
}
