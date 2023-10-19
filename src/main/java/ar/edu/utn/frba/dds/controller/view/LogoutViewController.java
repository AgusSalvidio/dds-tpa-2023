package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;

public class LogoutViewController {
  ApplicationContext applicationContext;

  public LogoutViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    context.sessionAttribute("user_id", null);
    context.sessionAttribute("user_authorization_role", null);
    context.redirect("/login");
  }

}
