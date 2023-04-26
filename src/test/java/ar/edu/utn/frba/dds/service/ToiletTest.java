package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToiletTest {
    private Toilet toilet;
    private Section sectionA;
    private Section sectionB;
    @BeforeEach
    public void init() {
        this.toilet = new Toilet();
        this.toilet.setName("Toilet Primer Piso");

        this.sectionA = new Section();
        this.sectionA.setName("Acceso Principal a Estacion");
        this.sectionB = new Section();
        this.sectionB.setName("Acceso a Servicios");
    }

    @Test
    public void toiletHasALotOfSections() {
        this.toilet.addNewSection(sectionA);
        this.toilet.addNewSection(sectionB);
        Assertions.assertEquals(2, this.toilet.getSections().size());
    }
}
