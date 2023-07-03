package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.EscalatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.SectionCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EscalatorTest {

  private Escalator escalator() {
    return new EscalatorCreationAddOn().escalatorA();
  }

  private Section sectionA() {
    return new SectionCreationAddOn().sectionA();
  }

  private Section sectionB() {
    return new SectionCreationAddOn().sectionB();
  }

  @Test
  @DisplayName("Escalator has a lot of sections")
  public void escalatorHasALotOfSectionsTest() {

    Escalator escalator = this.escalator();
    escalator.addNewSection(this.sectionA());
    escalator.addNewSection(this.sectionB());

    Assertions.assertEquals(2, escalator.sections().size());
  }
}
