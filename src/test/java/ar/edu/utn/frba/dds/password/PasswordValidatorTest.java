package ar.edu.utn.frba.dds.password;

import ar.edu.utn.frba.dds.passwordvalidator.InvalidPassword;
import ar.edu.utn.frba.dds.passwordvalidator.NoRepeatingCharactersPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidator;
import ar.edu.utn.frba.dds.passwordvalidator.RarePasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.StrongPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.TopCommonPasswordsFileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

  static TopCommonPasswordsFileReader fileReader;
  static RarePasswordValidation rarePasswordValidation;
  static StrongPasswordValidation strongPasswordValidation;
  static NoRepeatingCharactersPasswordValidation noRepeatingCharactersPasswordValidation;

  @BeforeAll
  public static void createValidations() throws URISyntaxException {
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
