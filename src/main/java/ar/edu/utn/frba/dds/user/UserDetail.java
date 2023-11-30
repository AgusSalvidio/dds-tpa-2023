package ar.edu.utn.frba.dds.user;

import ar.edu.utn.frba.dds.converters.NotificationMeanConverter;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationMean;
import ar.edu.utn.frba.dds.service.Service;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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

  @Convert(converter = NotificationMeanConverter.class)
  @Column(name = "notification_mean")
  NotificationMean notificationMean;

  @Transient
  //@OneToOne
  //@JoinColumn(name = "preference_id", referencedColumnName = "id")
  UserPreference userPreference;

  public UserDetail() {
    //Sobrecarga para que no rompa Hibernate
  }

  public UserDetail(String name, String lastname, String anEmail, String telephone,
                    NotificationMean notificationMean) {
    this.name = name;
    this.lastname = lastname;
    this.email = anEmail;
    this.telephone = telephone;
    this.notificationMean = notificationMean;
    this.userPreference = new UserPreference();
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
                                      String telephone, NotificationMean notificationMean)
      throws Exception {
    /*
        Implemented this way because its needed an AssertionChecker class that will be implemented
        in another issue later on. Also should be necesary to specify the field thats empty
     */
    if (name.isEmpty() || lastname.isEmpty() || anEmail.isEmpty()
        || telephone.isEmpty() || notificationMean == null) {
      throw new Exception("Los campos no pueden estar en blanco.");
    }
    return new UserDetail(name, lastname, anEmail, telephone, notificationMean);
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
