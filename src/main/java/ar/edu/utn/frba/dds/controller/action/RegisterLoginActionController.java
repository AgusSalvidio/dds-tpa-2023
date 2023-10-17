package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.NoResultException;

public class RegisterLoginActionController implements Handler {

  ApplicationContext applicationContext;

  public RegisterLoginActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public boolean areValidCredentials(String username, String password) {
    try {
      User user = this.applicationContext.userNamed(username);
      return Objects.equals(user.username(), username)
          && Objects.equals(user.getPassword(), password);
    } catch (NullPointerException e) {
      return false;
    }
  }


  @Override
  public void handle(Context context) throws Exception {
    String username = context.formParam("username");
    String password = context.formParam("password");

    if (this.areValidCredentials(username, password)) {
      User user = this.applicationContext.userNamed(username);
      context.sessionAttribute("user_id", user.getId());
      context.redirect("/");
      context.sessionAttribute("login_failed", null);
    } else {
      context.sessionAttribute("login_failed", true);
      context.redirect("/login");
    }
  }

}
