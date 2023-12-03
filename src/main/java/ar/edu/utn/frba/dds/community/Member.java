package ar.edu.utn.frba.dds.community;

import ar.edu.utn.frba.dds.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "member")
@Setter
@Getter
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  User user;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  MemberRole role;

  public Member() {
  }

  public Member(User anUser, MemberRole role) {
    this.user = anUser;
    this.role = role;
  }

  public static Member composedOf(User anUser, MemberRole role) {
    return new Member(anUser, role);
  }

  public User user() {
    return this.user;
  }

  public MemberRole role() {
    return this.role;
  }

  public void synchronizedWith(Member updateMember) {
    this.user = updateMember.user();
    this.role = updateMember.role();
  }
}
