package ar.edu.utn.frba.dds.passwordvalidator;

import java.util.ArrayList;
import java.util.List;

public class PasswordCache {

  private List<String> passwords;

  public PasswordCache() {
    this.passwords = new ArrayList<>();
  }

  public void addNewPassword(String password) {
    this.passwords.add(password);
  }

  public boolean cacheAlreadyHas(String password) {
    return passwords.contains(password);
  }
}
