package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class LoginViewController implements Handler {

  ApplicationContext applicationContext;

  public LoginViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("title", "Ingreso");
    if (context.sessionAttribute("login_failed") != null
        && context.sessionAttribute("user_id") == null) {
      model.put("error", true);
    }
    context.render("login.hbs", model);
  }
}
