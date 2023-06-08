package ar.edu.utn.frba.dds.passwordvalidator;

public class InvalidPassword extends RuntimeException {

  public InvalidPassword(String message) {
    super(message);
  }
}
