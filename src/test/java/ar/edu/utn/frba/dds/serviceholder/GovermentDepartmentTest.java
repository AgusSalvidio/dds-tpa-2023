package ar.edu.utn.frba.dds.serviceholder;

import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GovermentDepartmentTest {

  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");

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

    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    govermentDepartment.addModerator(user);

    Assertions.assertTrue(govermentDepartment.members().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(govermentDepartment.moderators().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

  }

  @Test
  @DisplayName("Remove member from goverment Department")
  public void removeMemberGovermentDepartmentTest() throws Exception {

    GovermentDepartment govermentDepartment = GovermentDepartment.composedOf("CNRT", "Comisión Nacional de Regulación del Transporte");

    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    govermentDepartment.addAnalyst(user);

    Assertions.assertTrue(govermentDepartment.analysts().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());

    govermentDepartment.removeUser(user);

    Assertions.assertTrue(govermentDepartment.members().isEmpty());
    Assertions.assertTrue(govermentDepartment.moderators().isEmpty());
    Assertions.assertTrue(govermentDepartment.analysts().isEmpty());

  }
}
