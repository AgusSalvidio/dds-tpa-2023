package ar.edu.utn.frba.dds.notification;

import ar.edu.utn.frba.dds.user.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Msg {
  public String content;
  public User user;

  public Msg() {
    this.user = new User();
  }

  public Msg(String content, User user) {
    this.content = content;
    this.user = user;
  }
}
