package ar.edu.utn.frba.dds.passwordvalidator;

public class NoRepeatingCharactersPasswordValidation implements PasswordValidation {
  @Override
  public boolean validatePassword(String password) {
    if (
        !password.matches(
            "^(?!.*(\\w)\\1+).+$")) {
      return false;
    }
    return true;
  }
}
