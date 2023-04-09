package ar.edu.utn.frba.dds.userDetails;

public class UserDetails {
  String name;
  String lastname;
  String email;
    public UserDetails(String aName,String aLastname,String anEmail){
    this.name = aName;
    this.lastname = aLastname;
    this.email = anEmail;
  }
  public String name(){
      return this.name;
  }
  public String lastname(){
      return this.lastname;
  }
  public String email(){
      return this.email;
  }
  public static UserDetails composedBy(String aName, String aLastname, String anEmail) throws Exception {
      /*
        Implemented this way because its needed an AssertionChecker class that will be implemented
        in another issue later on. Also should be necesary to specify the field thats empty -asalvidio
      */
      if(aName.isEmpty() || aLastname.isEmpty() || anEmail.isEmpty())
        throw new Exception("Los campos no pueden estar en blanco.");
      return new UserDetails(aName,aLastname,anEmail);
  }
}
