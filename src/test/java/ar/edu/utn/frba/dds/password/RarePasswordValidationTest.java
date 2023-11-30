package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.InvalidPassword;
import ar.edu.utn.frba.dds.passwordvalidator.RarePasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.TopCommonPasswordsFileReader;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RarePasswordValidationTest {

  static TopCommonPasswordsFileReader fileReader;
  static RarePasswordValidation rarePasswordValidation;

  @BeforeAll
  public static void createValidation() throws URISyntaxException {
    fileReader = new TopCommonPasswordsFileReader(100);
    fileReader.sortPasswords();

    rarePasswordValidation = new RarePasswordValidation(fileReader);
  }

  @Test
  @DisplayName("Passwords in the top 10000 most common password are not strong enough.")
  public void passwordIsInTheTop10000MostCommonPasswordsFileTest() {
    Assertions.assertThrows(
        InvalidPassword.class, () -> rarePasswordValidation.validatePassword("password"),
        "Password is not strong enough.");
  }
}
