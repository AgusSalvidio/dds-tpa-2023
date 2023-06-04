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

  private boolean findPasswordInFileAndAddToCache(String password) throws IOException {
    if (!passwordsFileReader.assertIsInFile(password)) {
      passwordCache.addNewPassword(password);
      return false;
    }
    return true;
  }

  private boolean assertCacheAlreadyHas(String password) throws IOException {
    if (passwordCache.cacheAlreadyHas(password)) {
      return false;
    }
    return findPasswordInFileAndAddToCache(password);
  }

  @Override
  public boolean validatePassword(String password) {

    try {
      return this.assertCacheAlreadyHas(password);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
