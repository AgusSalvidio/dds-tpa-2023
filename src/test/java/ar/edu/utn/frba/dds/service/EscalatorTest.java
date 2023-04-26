package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EscalatorTest {
    private Escalator escalator;
    private Section sectionA;
    private Section sectionB;
    @BeforeEach
    public void init() {
        this.escalator = new Escalator();
        this.escalator.setName("Escalera Mecanica Adaptada");

        this.sectionA = new Section();
        this.sectionA.setName("Acceso Principal a Molinetes");
        this.sectionB = new Section();
        this.sectionB.setName("Acceso a Escalera");
    }

    @Test
    public void escalatorHasALotOfSections() {
        this.escalator.addNewSection(sectionA);
        this.escalator.addNewSection(sectionB);
        Assertions.assertEquals(2, this.escalator.getSections().size());
    }
}
