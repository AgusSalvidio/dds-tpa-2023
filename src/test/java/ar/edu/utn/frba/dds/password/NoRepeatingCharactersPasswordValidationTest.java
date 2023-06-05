package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.InvalidPassword;
import ar.edu.utn.frba.dds.passwordvalidator.NoRepeatingCharactersPasswordValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NoRepeatingCharactersPasswordValidationTest {

  static NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation;

  @BeforeAll
  public static void createValidation() {
    noRepeatingCharactersPasswordValidation = new NoRepeatingCharactersPasswordValidation();
  }

  @Test
  @DisplayName("Password with consecutive repeating characters is not strong enough.")
  public void passwordWithRepeatingCharactersIsNotStrongEnoughTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> noRepeatingCharactersPasswordValidation.validatePassword("QQfs"),
        "Password is not strong enough.");
  }
}
