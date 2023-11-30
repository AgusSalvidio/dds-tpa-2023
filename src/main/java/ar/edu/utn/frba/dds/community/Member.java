package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.user.User;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Table(name = "member")
public class Member {
  @Id
  @GeneratedValue
  @Setter
  @Getter
  Integer id;

  @Getter
  @OneToOne
  @JoinTable(name = "user")
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;

  @Getter
  @Column(name = "role")
  String role;

  public Member() {
  }

  public Member(User anUser, String role) {
    this.user = anUser;
    this.role = role;
  }

  public static Member composedOf(User anUser, String role) {
    return new Member(anUser, role);
  }

  public User user() {
    return this.user;
  }

  public String role() {
    return this.role;
  }

  public void synchronizedWith(Member updateMember) {
    this.user = updateMember.user();
    this.role = updateMember.role();
  }
}
