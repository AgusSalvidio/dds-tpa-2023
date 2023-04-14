package ar.edu.utn.frba.dds.passwordvalidator;

public class StrongPasswordValidation implements PasswordValidation {

  @Override
  public void validatePassword(String password) {
    if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$)(?=([A-Za-z])(?!.\\1)).{8,20}$")) {
      throw new RuntimeException("The password is not strong enough.");
    }
  }
}
