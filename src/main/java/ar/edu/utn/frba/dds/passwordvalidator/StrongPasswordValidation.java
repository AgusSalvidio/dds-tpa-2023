package ar.edu.utn.frba.dds.passwordvalidator;

public class StrongPasswordValidation implements PasswordValidation {

  @Override
  public boolean validatePassword(String password) {
    return password.matches(
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()|])(?=\\S+$).{8,20}$");
  }
}
