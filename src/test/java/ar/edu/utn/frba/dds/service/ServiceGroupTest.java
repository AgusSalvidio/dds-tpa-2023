package ar.edu.utn.frba.dds.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ServiceGroupTest {
    private ServiceGroup serviceGroup;
    private Elevator elevator;
    private Escalator escalator;
    private Toilet toilet;
    private Section sectionElevatorA;
    private Section sectionElevatorB;
    private Section sectionEscalatorA;
    private Section sectionEscalatorB;
    private Section sectionToiletA;
    private Section sectionToiletB;

    @BeforeEach
    public void init() {
        this.serviceGroup = new ServiceGroup();
        this.elevator = new Elevator();
        this.elevator.setName("Ascensor Principal");
        this.sectionElevatorA = new Section();
        this.sectionElevatorA.setName("Acceso Principal a Molinetes");
        this.sectionElevatorB = new Section();
        this.sectionElevatorB.setName("Acceso a Plataforma");
        this.escalator = new Escalator();
        this.escalator.setName("Escalera Mecanica Adaptada");
        this.sectionEscalatorA = new Section();
        this.sectionEscalatorA.setName("Acceso Principal a Molinetes");
        this.sectionEscalatorB = new Section();
        this.sectionEscalatorB.setName("Acceso a Escalera");
        this.toilet = new Toilet();
        this.toilet.setName("Toilet Primer Piso");
        this.sectionToiletA = new Section();
        this.sectionToiletA.setName("Acceso Principal a Estacion");
        this.sectionToiletB = new Section();
        this.sectionToiletB.setName("Acceso a Servicios");
    }

    @Test
    @DisplayName("Group of different services")
    public void groupOfDifferentServices() {
        this.elevator.addNewSection(sectionElevatorA);
        this.elevator.addNewSection(sectionElevatorB);
        this.escalator.addNewSection(sectionEscalatorA);
        this.escalator.addNewSection(sectionEscalatorB);
        this.toilet.addNewSection(sectionToiletA);
        this.toilet.addNewSection(sectionToiletB);
        this.serviceGroup.addNewService(elevator);
        this.serviceGroup.addNewService(escalator);
        this.serviceGroup.addNewService(toilet);
        Assertions.assertEquals(3, this.serviceGroup.getServices().size());
    }
}
