package ar.edu.utn.frba.dds.passwordvalidator;

import java.io.IOException;

public class RarePasswordValidation implements PasswordValidation {
  private final TopCommonPasswordsFileReader passwordsFileReader;

  public RarePasswordValidation(TopCommonPasswordsFileReader passwordsFileReader) {
    this.passwordsFileReader = passwordsFileReader;
  }

  @Override
  public boolean validatePassword(String password) {

    try {
      return passwordsFileReader.findPassword(password);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
