package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElevatorTest {
    private Elevator elevator;
    private Section sectionA;
    private Section sectionB;
    @BeforeEach
    public void init() {
        this.elevator = new Elevator();
        this.elevator.setName("Ascensor Principal");

        this.sectionA = new Section();
        this.sectionA.setName("Acceso Principal a Molinetes");
        this.sectionB = new Section();
        this.sectionB.setName("Acceso a Plataforma");
    }

    @Test
    public void elevatorHasALotOfSections() {
        this.elevator.addNewSection(sectionA);
        this.elevator.addNewSection(sectionB);
        Assertions.assertEquals(2, this.elevator.getSections().size());
    }
}
