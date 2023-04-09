package ar.edu.utn.frba.dds.userdetails;

public class UserDetails {
  String name;
  String lastname;
  String email;

  /**
   * UserDetails contains more info for the User.
   */
  public UserDetails(String name, String lastname, String anEmail) {
    this.name = name;
    this.lastname = lastname;
    this.email = anEmail;
  }

  public String name() {
    return this.name;
  }

  public String lastname() {
    return this.lastname;
  }

  public String email() {
    return this.email;
  }

  /**
   * Another instance creation method but can make assertions.
   */
  public static UserDetails composedBy(String name, String lastname, String anEmail)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker class that will be implemented
        in another issue later on. Also should be necesary to specify the field thats empty
     */
    if (name.isEmpty() || lastname.isEmpty() || anEmail.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new UserDetails(name, lastname, anEmail);
  }
}
