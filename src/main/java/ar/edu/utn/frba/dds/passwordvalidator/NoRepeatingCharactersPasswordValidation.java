package ar.edu.utn.frba.dds.passwordvalidator;

public class NoRepeatingCharactersPasswordValidation implements PasswordValidation {
  @Override
  public void validatePassword(String password) {
    if (
        !password.matches(
            "^(?=([A-Za-z])(?!.\\1))")) {
      throw new RuntimeException("The password is not strong enough.");
    }
  }
}
