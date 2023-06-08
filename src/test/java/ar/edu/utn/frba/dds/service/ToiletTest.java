package ar.edu.utn.frba.dds.service;

import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.SectionCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ToiletCreationAddOn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ToiletTest {
  private Toilet toiletA() {
    return new ToiletCreationAddOn().toiletA();
  }

  private Section sectionA() {
    return new SectionCreationAddOn().sectionA();
  }

  private Section sectionB() {
    return new SectionCreationAddOn().sectionB();
  }

  @Test
  @DisplayName("Toilet has a lot of sections")
  public void toiletHasALotOfSectionsTest() {
    Toilet toilet = this.toiletA();

    toilet.addNewSection(this.sectionA());
    toilet.addNewSection(this.sectionB());

    Assertions.assertEquals(2, toilet.getSections().size());
  }
}
