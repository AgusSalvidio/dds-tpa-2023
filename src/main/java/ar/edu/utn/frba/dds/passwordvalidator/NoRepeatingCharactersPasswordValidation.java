package ar.edu.utn.frba.dds.passwordvalidator;

public class NoRepeatingCharactersPasswordValidation implements PasswordValidation {
  @Override
  public void validatePassword(String password) {

    if (!password.matches("^(?!.*(\\w)\\1+).+$")) {
      throw new InvalidPassword("Password is not strong enough.");
    }
  }
}
