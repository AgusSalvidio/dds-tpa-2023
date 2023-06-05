package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PasswordValidatorTest {

  static TopCommonPasswordsFileReader fileReader;
  static RarePasswordValidation rarePasswordValidation;
  static StrongPasswordValidation strongPasswordValidation;
  static NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation;

  @BeforeAll
  public static void createFileReader() throws URISyntaxException {
    fileReader = new TopCommonPasswordsFileReader(100);
    fileReader.sortPasswords();

    rarePasswordValidation = new RarePasswordValidation(fileReader);
    strongPasswordValidation = new StrongPasswordValidation();
    noRepeatingCharactersPasswordValidation = new NoRepeatingCharactersPasswordValidation();
  }

  @Test
  @DisplayName("Create password validator.")
  public void createPasswordValidatorTest() {
    List<PasswordValidation> validations = new ArrayList<>();
    validations.add(strongPasswordValidation);
    validations.add(rarePasswordValidation);
    validations.add(noRepeatingCharactersPasswordValidation);

    PasswordValidator validator = new PasswordValidator(validations);

    Assertions.assertEquals(validator.validations(), validations);
  }

  @Test
  @DisplayName("Passwords in the top 10000 most common password are not strong enough.")
  public void passwordIsInTheTop10000MostCommonPasswordsFileTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> rarePasswordValidation.validatePassword("password"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password without under case letters is not strong enough.")
  public void passwordWithoutUnderCaseLettersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> strongPasswordValidation.validatePassword("QPN|156#"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password without upper case letters is not strong enough.")
  public void passwordWithoutUpperCaseLettersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> strongPasswordValidation.validatePassword("qpn|156#"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password without special characters is not strong enough.")
  public void passwordWithoutSpecialCharactersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> strongPasswordValidation.validatePassword("QpNx1560"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password with less than 8 characters is not strong enough.")
  public void passwordWithLessThan8CharactersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> strongPasswordValidation.validatePassword("QpN|156"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password with spaces is not strong enough.")
  public void passwordWithWhiteSpacesIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> strongPasswordValidation.validatePassword("QpN| 15#6"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password with consecutive repeating characters is not strong enough.")
  public void passwordWithRepeatingCharactersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> noRepeatingCharactersPasswordValidation.validatePassword("QQfs"),
        "Password is not strong enough.");
  }

  @Test
  @DisplayName("Password validator.")
  public void passwordValidatorTest() {
    List<PasswordValidation> validations = new ArrayList<>();
    validations.add(strongPasswordValidation);
    validations.add(rarePasswordValidation);
    validations.add(noRepeatingCharactersPasswordValidation);

    PasswordValidator validator = new PasswordValidator(validations);

    Assertions.assertThrows(
        InvalidPassword.class, () -> validator.validateIfPasswordIsAllowed("QQpNx1560"),
        "Password is not strong enough.");
  }
}
