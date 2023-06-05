package ar.edu.utn.frba.dds.passwordvalidator;

import java.util.ArrayList;
import java.util.List;

public class PasswordCache {

  private List<String> passwords;
  private int limitOfWords;
  private int currentPosition = 0;

  public PasswordCache(int limitOfWords) {
    this.passwords = new ArrayList<>();
    this.limitOfWords = limitOfWords;
  }

  public void addNewPassword(String password) {

    if (currentPosition == limitOfWords) {
      this.passwords.remove(0);
      currentPosition--;
    }

    this.passwords.add(currentPosition, password);
    currentPosition++;
  }

  public boolean alreadyHas(String password) {
    return passwords.contains(password);
  }
}
