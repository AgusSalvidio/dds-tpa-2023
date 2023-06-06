package ar.edu.utn.frba.dds.serviceholder;

import ar.edu.utn.frba.dds.addons.entitycreationaddon.TransportLineCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GovermentDepartmentTest {

  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();

  }

  private Elevator elevator() throws Exception {
    return new ElevatorCreationAddOn().elevator();

  }

  private TransportLine transportLineA() {
    return new TransportLineCreationAddOn().transportA();
  }

  @Test
  @DisplayName("Create a goverment department")
  public void createAGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");

    Assertions.assertEquals(govermentDepartment.name(), "CNRT");
    Assertions.assertEquals(govermentDepartment.description(), "Comisión Nacional de Regulación del Transporte");
    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

  }

  @Test
  @DisplayName("When creating a goverment department with any field empty should raise error")
  public void cannotCreateGovermentdepartmentWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> Company.composedOf("", "Comisión Nacional de Regulación del Transporte"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> Company.composedOf("CNRT", ""), "Los campos no pueden estar en blanco.");
  }

  @Test
  @DisplayName("Add a moderator to goverment department")
  public void addModeratorToGovermentDepartmentTest() throws Exception {

    Company govermentDepartment = Company.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");
    User user = this.ibarraneta();

    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

    govermentDepartment.addModerator(user);

    Assertions.assertTrue(govermentDepartment.members().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(govermentDepartment.moderators().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

  }

  @Test
  @DisplayName("Remove member from goverment Department")
  public void removeMemberGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");
    User user = this.ibarraneta();

    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

    govermentDepartment.addAnalyst(user);

    Assertions.assertTrue(govermentDepartment.analysts().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());

    govermentDepartment.removeUser(user);

    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

  }

  @Test
  @DisplayName("Add a service to govermentDepartment")
  public void addServiceToGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");
    Elevator elevator = this.elevator();

    Assertions.assertTrue(govermentDepartment.services().isEmpty());
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

    govermentDepartment.addService(elevator);

    Assertions.assertTrue(govermentDepartment.services().contains(elevator));
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

  }

  @Test
  @DisplayName("Remove service from govermentDepartment")
  public void removeServiceGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");
    Elevator elevator = this.elevator();

    Assertions.assertTrue(govermentDepartment.services().isEmpty());
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

    govermentDepartment.addService(elevator);

    Assertions.assertTrue(govermentDepartment.services().contains(elevator));
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

    govermentDepartment.removeService(elevator);

    Assertions.assertTrue(govermentDepartment.services().isEmpty());
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

  }

  @Test
  @DisplayName("Add a entity to govermentDepartment")
  public void addEntityToGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");
    TransportLine transport = this.transportLineA();

    Assertions.assertTrue(govermentDepartment.services().isEmpty());
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

    govermentDepartment.addEntity(transport);

    Assertions.assertTrue(govermentDepartment.entities().contains(transport));
    Assertions.assertTrue(govermentDepartment.services().isEmpty());

  }

  @Test
  @DisplayName("Remove entity from govermentDepartment")
  public void removeEntityGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");
    TransportLine transport = this.transportLineA();

    Assertions.assertTrue(govermentDepartment.services().isEmpty());
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

    govermentDepartment.addEntity(transport);

    Assertions.assertTrue(govermentDepartment.entities().contains(transport));
    Assertions.assertTrue(govermentDepartment.services().isEmpty());

    govermentDepartment.removeEntity(transport);

    Assertions.assertTrue(govermentDepartment.services().isEmpty());
    Assertions.assertTrue(govermentDepartment.entities().isEmpty());

  }

}
