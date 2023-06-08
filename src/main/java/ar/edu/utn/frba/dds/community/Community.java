package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Community {

  String name;
  String description;
  List<Member> members = new ArrayList<>();
  List<Service> services = new ArrayList<>();
  List<Entity> entities = new ArrayList<>();

  public static Community composedOf(String name, String description)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker that will be implemented
        in another issue later on. Also should be necessary to specify the field thats empty.
      */
    if (name.isEmpty() || description.isEmpty()) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new Community(name, description);

  }

  public Community(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String name() {
    return this.name;
  }

  public String description() {
    return this.description;
  }

  public List<Member> members() {
    return this.members.stream()
        .collect(Collectors.toList());
  }

  /*Made this way to pass codeSmells check*/
  public List<Service> services() {
    return this.services.stream()
        .collect(Collectors.toList());
  }

  public List<Entity> entities() {
    return this.entities.stream()
        .collect(Collectors.toList());
  }

  /*Made this way to pass codeSmells check*/
  public List<Member> moderators() {
    return this.members().stream()
        .filter(member -> member.role()
            .equals("Moderador"))
        .collect(Collectors.toList());
  }

  private void removeMemberComposedOf(User anUser) {
    Member foundMember = this.members.stream()
        .filter(member -> member.user().equals(anUser))
        .collect(Collectors.toList()).get(0);

    members.remove(foundMember);

  }

  private void addMemberComposedOf(User anUser, String role) {
    Member newMember = Member.composedOf(anUser, role);
    this.members.add(newMember);
  }

  public void addModerator(User anUser) {
    this.addMemberComposedOf(anUser, "Moderador");
  }

  public void addUser(User anUser) {
    this.addMemberComposedOf(anUser, "Suscriptor");
  }

  public void removeUser(User anUser) {
    this.removeMemberComposedOf(anUser);
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public void addTransportLine(Entity entity) {
    this.entities.add(entity);
  }

  public void synchronizeWith(Community anUpdatedCommunity) {
    this.members = anUpdatedCommunity.members;
    this.services = anUpdatedCommunity.services;
  }

}
