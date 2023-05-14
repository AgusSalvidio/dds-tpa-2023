package ar.edu.utn.frba.dds.serviceholder;

public class GovermentDepartment extends ServiceHolder {

  public static GovermentDepartment composedOf(String name, String description)
      throws Exception {
    /*
        Similar implementation to Community... Should think to incorporate community
        and this class into a super class. This will be more clear when definitions
        for both AuthorizationRoles are more clear -asalvidio.
      */
    if (name.isEmpty() || description.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new GovermentDepartment(name, description);

  }

  public GovermentDepartment(String name, String description) {
    this.name = name;
    this.description = description;
  }
}
