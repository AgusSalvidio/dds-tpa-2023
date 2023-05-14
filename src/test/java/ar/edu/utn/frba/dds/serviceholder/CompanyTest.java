package ar.edu.utn.frba.dds.serviceholder;

import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CompanyTest {

  private UserDetails userDetails() {
    return new UserDetails("Hugo", "Ibarra", "ibarraneta@gmail.com");

  }

  @Test
  @DisplayName("Create a company")
  public void createACompanyTest() throws Exception {

    Company company = Company.composedOf("Taranga & Compania", "Empresa de servicios");

    Assertions.assertEquals(company.name(), "Taranga & Compania");
    Assertions.assertEquals(company.description(), "Empresa de servicios");
    Assertions.assertTrue(company.members().isEmpty());
    Assertions.assertTrue(company.moderators().isEmpty());
    Assertions.assertTrue(company.analysts().isEmpty());

  }

  @Test
  @DisplayName("When creating a company with any field empty should raise error")
  public void cannotCreateCompanyWhenAnyFieldIsEmpty() throws Exception {
    Assertions.assertThrows(Exception.class, () -> Company.composedOf("", "Empresa de servicios"), "Los campos no pueden estar en blanco.");
    Assertions.assertThrows(Exception.class, () -> Company.composedOf("Taranga & Compania", ""), "Los campos no pueden estar en blanco.");
  }

  @Test
  @DisplayName("Add a moderator to company")
  public void addModeratorToCompanyTest() throws Exception {

    Company company = Company.composedOf("Taranga & Compania", "Empresa de servicios");

    Assertions.assertTrue(company.members().isEmpty());
    Assertions.assertTrue(company.moderators().isEmpty());
    Assertions.assertTrue(company.analysts().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    company.addModerator(user);

    Assertions.assertTrue(company.members().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(company.moderators().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(company.analysts().isEmpty());

  }

  @Test
  @DisplayName("Remove member from company")
  public void removeMemberCompanyTest() throws Exception {

    Company company = Company.composedOf("Taranga & Compania", "Empresa de servicios");

    Assertions.assertTrue(company.members().isEmpty());
    Assertions.assertTrue(company.moderators().isEmpty());
    Assertions.assertTrue(company.analysts().isEmpty());

    User user = User.composedOf("ibarranetaYPF", "theBestPassword", this.userDetails());
    company.addAnalyst(user);

    Assertions.assertTrue(company.analysts().stream().anyMatch(member -> member.user().equals(user)));
    Assertions.assertTrue(company.moderators().isEmpty());

    company.removeUser(user);

    Assertions.assertTrue(company.members().isEmpty());
    Assertions.assertTrue(company.moderators().isEmpty());
    Assertions.assertTrue(company.analysts().isEmpty());

  }
}
