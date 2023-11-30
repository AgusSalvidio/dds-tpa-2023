package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginViewController {

  ApplicationContext applicationContext;

  public LoginViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("title", "Ingreso");
    if (context.sessionAttribute("login_failed") != null
        && context.sessionAttribute("user_id") == null) {
      model.put("error", true);
    }
    context.render("login.hbs", model);
  }

  public boolean areValidCredentials(String username, String password) {
    User user = this.applicationContext.userNamed(username);
    if (user != null) {
      return Objects.equals(user.username(), username)
          && Objects.equals(user.getPassword(), password);
    } else {
      return false;
    }
  }

  public void save(Context context) throws Exception {
    String username = context.formParam("username");
    String password = context.formParam("password");

    if (this.areValidCredentials(username, password)) {
      User user = this.applicationContext.userNamed(username);
      context.sessionAttribute("user_id", user.getId());
      context.sessionAttribute("user_authorization_role", user.authorizationRole().toString());
      context.redirect("/");
      context.sessionAttribute("login_failed", null);
    } else {
      context.sessionAttribute("login_failed", true);
      context.redirect("/login");
    }
  }
}
