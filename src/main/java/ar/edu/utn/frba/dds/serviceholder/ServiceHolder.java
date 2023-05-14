package ar.edu.utn.frba.dds.serviceholder;

import ar.edu.utn.frba.dds.community.Member;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ServiceHolder {
  String name;
  String description;
  List<Member> members = new ArrayList<>();

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

  public List<Member> analysts() {
    return this.members().stream()
        .filter(member -> member.role()
            .equals("Analista"))
        .collect(Collectors.toList());
  }

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

  public void addAnalyst(User anUser) {
    this.addMemberComposedOf(anUser, "Analista");
  }

  public void addModerator(User anUser) {
    this.addMemberComposedOf(anUser, "Moderador");
  }

  public void removeUser(User anUser) {
    this.removeMemberComposedOf(anUser);
  }

}
