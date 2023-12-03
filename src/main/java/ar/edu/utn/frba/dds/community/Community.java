package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "community")
@Setter
@Getter
public class Community {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @Column(name = "name")
  String name;

  @Column(name = "description")
  String description;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  List<Member> members;

  @Transient
  List<Service> services;

  @Transient
  List<Entity> entities;

  @Transient
  List<Incident> openIncidents;

  public static Community composedOf(String name, String description) {
    return new Community(name, description);
  }

  public Community() {
  }

  public Community(String name, String description) {
    this.name = name;
    this.description = description;
    this.members = new ArrayList<>();
    this.entities = new ArrayList<>();
    this.openIncidents = new ArrayList<>();
    this.services = new ArrayList<>();
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

  public List<Incident> openIncidents() {
    return this.openIncidents.stream()
        .collect(Collectors.toList());
  }

  /*This should be made by the notificationSystem or by anotherObject
    depends on the implementation chosen -asalvidio */
  public void addOpenIncident(Incident incident) {
    this.openIncidents.add(incident);
    /*Here we have to decide to notify the IncidentPerCommunityManagementSystem
    to add IncidentPerCommunity or do it before depends on the implementation -asalvidio */
  }

  public void removeIncident(Incident incident) {
    this.openIncidents.remove(incident);
    /*Here we have to decide to notify the IncidentPerCommunityManagementSystem to
    change the status to closed -asalvidio */
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
            .equals(MemberRole.MODERADOR))
        .collect(Collectors.toList());
  }

  public List<Member> affected() {
    return this.members().stream()
        .filter(member -> member.role()
            .equals(MemberRole.AFECTADO))
        .collect(Collectors.toList());
  }

  private void removeMemberComposedOf(User anUser) {
    Member foundMember = this.members.stream()
        .filter(member -> member.user().equals(anUser))
        .collect(Collectors.toList()).get(0);

    members.remove(foundMember);

  }

  public void addMemberComposedOf(User anUser, MemberRole role) {
    Member newMember = Member.composedOf(anUser, role);
    this.members.add(newMember);
  }

  public void addMember(Member member) {
    this.members.add(member);
  }

  public void addModerator(User anUser) {
    this.addMemberComposedOf(anUser, MemberRole.MODERADOR);
  }

  public void addUser(User anUser) {
    this.addMemberComposedOf(anUser, MemberRole.SUSCRIPTOR);
  }

  public void addAffected(User anUser) {
    this.addMemberComposedOf(anUser, MemberRole.AFECTADO);
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
