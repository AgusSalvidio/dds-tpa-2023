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
}
