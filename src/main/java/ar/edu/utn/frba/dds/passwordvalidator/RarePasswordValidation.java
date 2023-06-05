package ar.edu.utn.frba.dds.passwordvalidator;

public class RarePasswordValidation implements PasswordValidation {
  private final TopCommonPasswordsFileReader passwordsFileReader;

  public RarePasswordValidation(
      TopCommonPasswordsFileReader passwordsFileReader) {
    this.passwordsFileReader = passwordsFileReader;
  }

  @Override
  public void validatePassword(String password) {
    passwordsFileReader.assertIsInFileOrCache(password);
  }
}
