package ar.edu.utn.frba.dds.passwordvalidator;

public class NoRepeatingCharactersPasswordValidation implements PasswordValidation {
  @Override
  public boolean validatePassword(String password) {
    return password.matches(
        "^(?!.*(\\w)\\1+).+$");
  }
}
