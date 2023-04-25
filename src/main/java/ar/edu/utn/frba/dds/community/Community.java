package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Community {
  List<Member> members = new ArrayList<>();
  List<Service> services = new ArrayList<>();

  public List<Member> members() {
    return this.members.stream()
        .collect(Collectors.toList());
  }

  public List<Service> services() {
    return this.services.stream()
        .collect(Collectors.toList());
  }

  public List<Member> moderators() {
    return this.members().stream()
        .filter(member -> member.role()
            .equals("Moderador"))
        .collect(Collectors.toList());
  }

  private void removeMemberComposedBy(User anUser) {
    Member foundMember = this.members.stream()
        .filter(member -> member.user().equals(anUser))
        .collect(Collectors.toList()).get(0);

    members.remove(foundMember);

  }

  private void addMemberComposedBy(User anUser, String role) {
    Member newMember = Member.composedBy(anUser, role);
    this.members.add(newMember);
  }

  public void addModerator(User anUser) {
    this.addMemberComposedBy(anUser, "Moderador");
  }

  public void addUser(User anUser) {
    this.addMemberComposedBy(anUser, "Suscriptor");
  }

  public void removeUser(User anUser) {
    this.removeMemberComposedBy(anUser);
  }

  public void addService(Service service) {
    this.services.add(service);
  }

  public void synchronizeWith(Community anUpdatedCommunity) {
    this.members = anUpdatedCommunity.members;
    this.services = anUpdatedCommunity.services;
  }

}
