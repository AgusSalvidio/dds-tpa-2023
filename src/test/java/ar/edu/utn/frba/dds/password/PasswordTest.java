package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.NoRepeatingCharactersPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidator;
import ar.edu.utn.frba.dds.passwordvalidator.RarePasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.StrongPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.TopCommonPasswordsFileReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PasswordTest {

  @Test
  @DisplayName("Passwords in the top 10000 most common password are not strong enough.")
  public void passwordIsInTheTop10000MostCommonPasswordsFileTest() {
    RarePasswordValidation rarePasswordValidation = new RarePasswordValidation(new TopCommonPasswordsFileReader());

    Assertions.assertFalse(rarePasswordValidation.validatePassword("password"));
  }

  @Test
  @DisplayName("Password without under case letters is not strong enough.")
  public void passwordWithoutUnderCaseLettersIsNotStrongEnoughTest() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QPN|156#"));
  }

  @Test
  @DisplayName("Password without upper case letters is not strong enough.")
  public void passwordWithoutUpperCaseLettersIsNotStrongEnoughTest() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("qpn|156#"));
  }

  @Test
  @DisplayName("Password without special characters is not strong enough.")
  public void passwordWithoutSpecialCharactersIsNotStrongEnoughTest() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QpNx1560"));
  }

  @Test
  @DisplayName("Password with less than 8 characters is not strong enough.")
  public void passwordWithLessThan8CharactersIsNotStrongEnoughTest() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QpN|156"));
  }

  @Test
  @DisplayName("Password with spaces is not strong enough.")
  public void passwordWithWhiteSpacesIsNotStrongEnoughTest() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();

    Assertions.assertFalse(strongPasswordValidation.validatePassword("QpN| 15#6"));
  }

  @Test
  @DisplayName("Password with consecutive repeating characters is not strong enough.")
  public void passwordWithRepeatingCharactersIsNotStrongEnoughTest() {
    NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation =
        new NoRepeatingCharactersPasswordValidation();

    Assertions.assertFalse(noRepeatingCharactersPasswordValidation.validatePassword("QQfs"));
  }

  @Test
  @DisplayName("Password validator.")
  public void passwordValidatorTest() {
    List<PasswordValidation> validations = new ArrayList<>();
    validations.add(new StrongPasswordValidation());
    validations.add(new RarePasswordValidation(new TopCommonPasswordsFileReader()));
    validations.add(new NoRepeatingCharactersPasswordValidation());

    PasswordValidator validator = new PasswordValidator(validations);

    Assertions.assertFalse(validator.validateIfPasswordIsAllowed("QQpNx1560"));
  }

  @Test
  @DisplayName("Strong password.")
  public void strongPasswordTest() {
    StrongPasswordValidation strongPasswordValidation = new StrongPasswordValidation();
    Assertions.assertTrue(strongPasswordValidation.validatePassword("QpN|15#6"));
  }

  @Test
  @DisplayName("Password without consecutive repeating characters is strong enough.")
  public void passwordWithoutRepeatingCharactersIsStrongEnoughTest() {
    NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation =
        new NoRepeatingCharactersPasswordValidation();

    Assertions.assertTrue(noRepeatingCharactersPasswordValidation.validatePassword("QpN|15#6"));
  }
}
