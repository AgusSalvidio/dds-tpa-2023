package ar.edu.utn.frba.dds.passwordvalidator;

import java.util.List;

public class PasswordValidator {
  public List<PasswordValidation> validations;

  public PasswordValidator(List<PasswordValidation> validations) {
    this.validations = validations;
  }

  public List<PasswordValidation> validations() {
    return this.validations;
  }

  public void validateIfPasswordIsAllowed(String password) {

    validations.forEach(validation -> validation.validatePassword(password));
  }
}
