package ar.edu.utn.frba.dds.passwordValidator;

import java.util.List;

public class PasswordValidator {
  public List<PasswordValidation> validations;

  public PasswordValidator(List<PasswordValidation> validations) {
    this.validations = validations;
  }

  public void validateIfPasswordIsAllowed(String password) {
    validations.forEach(validation -> validation.validatePassword(password));
  }
}
