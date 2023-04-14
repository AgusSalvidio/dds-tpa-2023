package ar.edu.utn.frba.dds;

import static org.mockito.Mockito.*;

import ar.edu.utn.frba.dds.passwordvalidator.NoRepeatingCharactersPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.PasswordValidator;
import ar.edu.utn.frba.dds.passwordvalidator.RarePasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.StrongPasswordValidation;
import ar.edu.utn.frba.dds.passwordvalidator.TopCommonPasswordsFileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class PasswordTest {
  static private PasswordValidator passwordValidator;

  @BeforeEach
  void initialValidator() {
    passwordValidator = mock(PasswordValidator.class);
  }
  @BeforeAll
  static void validationList() {
    List<PasswordValidation> validations = new ArrayList<>();
    validations.add(new StrongPasswordValidation());
    validations.add(new RarePasswordValidation(new TopCommonPasswordsFileReader()));
    validations.add(new NoRepeatingCharactersPasswordValidation());

    passwordValidator = new PasswordValidator(validations);
  }

  @Test
  public void passwordIsInTheTop10000MostCommonPasswordsFile() {
    doThrow(new RuntimeException("The password is not strong enough.")).when(passwordValidator).validateIfPasswordIsAllowed("123456");
  }

  @Test
  public void passwordWithoutUpperCaseLetterIsNotStrongEnough() {
    doThrow(new RuntimeException("The password is not strong enough.")).when(passwordValidator).validateIfPasswordIsAllowed("ap2674#@");
  }
}
