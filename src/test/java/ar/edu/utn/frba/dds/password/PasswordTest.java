package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.NoRepeatingCharactersPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidator;
import ar.edu.utn.frba.dds.passwordvalidator.RarePasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.StrongPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.TopCommonPasswordsFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PasswordTest {

  @Test
  public void passwordIsInTheTop10000MostCommonPasswordsFile() {
    RarePasswordValidation rarePasswordValidation = new RarePasswordValidation(new TopCommonPasswordsFileReader());

    Assertions.assertFalse(rarePasswordValidation.validatePassword("password"));
  }

  @Test
  public void passwordWithoutUnderCaseLettersIsNotStrongEnough() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QPN|156#"));
  }

  @Test
  public void passwordWithoutUpperCaseLettersIsNotStrongEnough() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("qpn|156#"));
  }

  @Test
  public void passwordWithoutSpecialCharactersIsNotStrongEnough() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QpNx1560"));
  }

  @Test
  public void passwordWithLessThan8CharactersIsNotStrongEnough() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QpN|156"));
  }

  @Test
  public void passwordWithWhiteSpacesIsNotStrongEnough() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QpN| 15#6"));
  }

  @Test
  public void passwordWithRepeatingCharactersIsNotStrongEnough() {
    NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation =
        new NoRepeatingCharactersPasswordValidation();

    Assertions.assertFalse(noRepeatingCharactersPasswordValidation.validatePassword("QQfs"));
  }

  @Test
  public void passwordIsNotStrongEnough() {
    List<PasswordValidation> validations = new ArrayList<>();
    validations.add(new StrongPasswordValidation());
    validations.add(new RarePasswordValidation(new TopCommonPasswordsFileReader()));
    validations.add(new NoRepeatingCharactersPasswordValidation());

    PasswordValidator validator = new PasswordValidator(validations);

    Assertions.assertFalse(validator.validateIfPasswordIsAllowed("QQpNx1560"));
  }

  @Test
  public void passwordIsStrongEnough() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();
    Assertions.assertTrue(strongPasswordValidation.validatePassword("QpN|15#6"));
  }

  @Test
  public void passwordWithoutRepeatingCharactersIsStrongEnough() {
    NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation =
        new NoRepeatingCharactersPasswordValidation();

    Assertions.assertTrue(noRepeatingCharactersPasswordValidation.validatePassword("QpN|15#6"));
  }
}
