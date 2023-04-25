package ar.edu.utn.frba.dds.passwordvalidator;

import java.util.List;

public class PasswordValidator {
  public List<PasswordValidation> validations;

  public PasswordValidator(List<PasswordValidation> validations) {
    this.validations = validations;
  }

  public boolean validateIfPasswordIsAllowed(String password) {

    return validations.stream().allMatch(validation -> validation.validatePassword(password));
  }
}
