package ar.edu.utn.frba.dds.serviceholder;

public class Company extends ServiceHolder {

  public static Company composedOf(String name, String description)
      throws Exception {
    /*
        Similar implementation to Community... Should think to incorporate community
        and this class into a super class. This will be more clear when definitions
        for both AuthorizationRoles are more clear -asalvidio.
      */
    if (name.isEmpty() || description.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new Company(name, description);

  }

  public Company(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
