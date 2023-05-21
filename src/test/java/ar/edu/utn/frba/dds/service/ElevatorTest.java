package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
    private Elevator elevator;
    private Section sectionA;
    private Section sectionB;
    private State stateA;
    private State stateB;

    @BeforeEach
    public void init() {
        this.elevator = new Elevator();
        this.elevator.setName("Ascensor Principal");
        this.sectionA = new Section();
        this.sectionA.setName("Acceso Principal a Molinetes");
        this.sectionB = new Section();
        this.sectionB.setName("Acceso a Plataforma");
        this.stateA = new State();
        this.stateA.setName("IN_SERVICE");
        this.stateB = new State();
        this.stateB.setName("NOT_IN_SERVICE");
    }

    @Test
    @DisplayName("Elevator has a lot of sections")
    public void elevatorHasALotOfSections() {
        this.elevator.addNewSection(sectionA);
        this.elevator.addNewSection(sectionB);
        Assertions.assertEquals(2, this.elevator.getSections().size());
    }

    @Test
    @DisplayName("Elevator is in service")
    public void elevatorIsInService() {
        this.elevator.setState(stateA);
        Assertions.assertEquals("IN_SERVICE", elevator.getState().getName());
    }

    @Test
    @DisplayName("Elevator is not in service")
    public void elevatorIsNotInService() {
        this.elevator.setState(stateB);
        Assertions.assertEquals("NOT_IN_SERVICE", elevator.getState().getName());
    }
}
