package ar.edu.utn.frba.dds.passwordvalidator;

import java.io.IOException;

public class RarePasswordValidation implements PasswordValidation {
  private final TopCommonPasswordsFileReader passwordsFileReader;
  private PasswordCache passwordCache;

  public RarePasswordValidation(
      TopCommonPasswordsFileReader passwordsFileReader) {
    this.passwordsFileReader = passwordsFileReader;
    this.passwordCache = new PasswordCache();
  }

  @Override
  public boolean validatePassword(String password) {

    try {
      if (passwordCache.cacheAlreadyHas(password)) {
        return false;
      }
      if (!passwordsFileReader.findPassword(password)) {
        passwordCache.addNewPassword(password);
        return false;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return true;
  }
}
