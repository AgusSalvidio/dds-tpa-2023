package ar.edu.utn.frba.dds.entity;

import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityNameCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EntityTypeCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.EstablishmentCreationAddOn;
import ar.edu.utn.frba.dds.addons.entitycreationaddon.TransportLineCreationAddOn;
import ar.edu.utn.frba.dds.establishment.Establishment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EntityTest {

  private Establishment stationA() {
    return new EstablishmentCreationAddOn().lawSchoolStation();
  }

  private Establishment branchA() {
    return new EstablishmentCreationAddOn().alsinaBranch();
  }

  private Establishment branchB() {
    return new EstablishmentCreationAddOn().headquarterBranch();
  }

  private TransportLine transportLineA() {
    return new TransportLineCreationAddOn().transportA();
  }

  private Organization organization() {
    return new Organization();
  }

  @Test
  @DisplayName("An establishment has a location which belongs to a municipality")
  public void anEstablishmentHasALocationWhichBelongsToAMunicipalityTest() {
    Assertions.assertEquals("CABA", this.stationA().getLocation().getMunicipality().name());
  }

  @Test
  @DisplayName("Sation and Branch are types of establishments")
  public void stationAndBranchClassesAreEstablishmentTypeTest() {

    Assertions.assertEquals("STATION", this.stationA().getType().getName());
    Assertions.assertEquals("BRANCH", this.branchA().getType().getName());
  }

  @Test
  @DisplayName("A transportLine has a name and belongs to a type")
  public void aTransportLineHasANameAndBelongsToATypeTest() {
    Assertions.assertEquals("SUBTE H", this.transportLineA().name());
    Assertions.assertEquals("SUBWAY", this.transportLineA().getType().getName());
  }

  @Test
  @DisplayName("A transportLine has a departure and an arrival station")
  public void aTransportLineHasADepartureAndAnArrivalStationTest() {
    Assertions.assertEquals("FACULTAD DE DERECHO", this.transportLineA().getDeparture().getName());
    Assertions.assertEquals("LAS HERAS", this.transportLineA().getArrival().getName());
  }

  @Test
  @DisplayName("An Organization has a name and belongs to a type")
  public void anOrganizationHasANameAndBelongsToATypeTest() {

    Organization bankOrganization = this.organization();

    bankOrganization.setName(new EntityNameCreationAddOn().nationalBank());
    bankOrganization.setType(new EntityTypeCreationAddOn().bank());
    Assertions.assertEquals("BANCO NACION", bankOrganization.name());
    Assertions.assertEquals("BANK", bankOrganization.getType().getName());
  }

  @Test
  @DisplayName("A bank has a lot of branchs")
  public void aBankHasALotOfBranchsTest() {
    Organization bankOrganization = this.organization();
    bankOrganization.addNewEstablishment(this.branchA());
    bankOrganization.addNewEstablishment(this.branchB());
    Assertions.assertEquals(2, bankOrganization.getEstablishments().size());
  }
}
