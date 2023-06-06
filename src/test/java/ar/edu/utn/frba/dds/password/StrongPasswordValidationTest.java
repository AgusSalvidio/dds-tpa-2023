package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.InvalidPassword;
import ar.edu.utn.frba.dds.passwordvalidator.StrongPasswordValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StrongPasswordValidationTest {

  static StrongPasswordValidation strongPasswordValidation;

  @BeforeAll
  public static void createValidation() {
    strongPasswordValidation = new StrongPasswordValidation();
  }

  @Test
  @DisplayName("Password without under case letters is not strong enough.")
  public void passwordWithoutUnderCaseLettersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> strongPasswordValidation.validatePassword("QPN|156#"),
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
}
