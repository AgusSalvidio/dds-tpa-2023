package ar.edu.utn.frba.dds.passwordValidator;

import java.io.IOException;

public class RarePasswordValidation implements PasswordValidation {
  private TopCommonPasswordsFileReader passwordsFileReader;

  public RarePasswordValidation(TopCommonPasswordsFileReader passwordsFileReader) {
    this.passwordsFileReader = passwordsFileReader;
  }

  @Override
  public void validatePassword(String password) {
    try {
      passwordsFileReader.findPassword(password);
    } catch (IOException error) {
      throw new RuntimeException("An error occurred while reading the file.");
    }
  }
}
