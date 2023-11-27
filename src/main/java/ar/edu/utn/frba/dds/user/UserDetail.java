package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.converters.NotificationMeanConverter;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationType;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@javax.persistence.Entity
@Table(name = "user_detail")
@Getter
@Setter
public class UserDetail {
  @Id
  @GeneratedValue
  Integer id;

  @Column(name = "name")
  String name;

  @Column(name = "lastname")
  String lastname;

  @Column(name = "email")
  String email;

  @Column(name = "telephone")
  String telephone;

  @Column(name = "notification_type")
  @Enumerated(EnumType.STRING)
  NotificationType notificationType;

  @Transient
  NotificationMean notificationMean;

  @Transient
  //@OneToOne
  //@JoinColumn(name = "preference_id", referencedColumnName = "id")
  UserPreference userPreference;

  public UserDetail() {
    //Sobrecarga para que no rompa Hibernate
  }
  public UserDetail(String name, String lastname, String anEmail, String telephone,
                    NotificationType notificationType) {
    this.name = name;
    this.lastname = lastname;
    this.email = anEmail;
    this.telephone = telephone;
    this.notificationType = notificationType;
    //this.userPreference = new UserPreference();
  }

  public String name() {
    return this.name;
  }

  public String lastname() {
    return this.lastname;
  }

  public String email() {
    return this.email;
  }

  public String telephone() {
    return this.telephone;
  }

  public void changeNotificationMean(NotificationMean newNotificationMean) {
    this.notificationMean = newNotificationMean;
  }

  public static UserDetail composedOf(String name, String lastname, String anEmail,
                                      String telephone, NotificationType notificationType)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker class that will be implemented
        in another issue later on. Also should be necesary to specify the field thats empty
     */
    if (name.isEmpty() || lastname.isEmpty() || anEmail.isEmpty()
        || telephone.isEmpty() || notificationType == null) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new UserDetail(name, lastname, anEmail, telephone, notificationType);
  }

  public List<Service> services() {
    return this.userPreference.services();
  }

  public List<Entity> entities() {
    return this.userPreference.entities();
  }

  public void synchronizeWith(UserDetail anUpdatedUserDetail) {
    this.name = anUpdatedUserDetail.name;
    this.lastname = anUpdatedUserDetail.lastname;
    this.email = anUpdatedUserDetail.email;
    this.telephone = anUpdatedUserDetail.telephone;
    this.notificationMean = anUpdatedUserDetail.notificationMean;
    this.userPreference = anUpdatedUserDetail.userPreference;
  }
}
