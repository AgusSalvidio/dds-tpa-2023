package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class LogoutActionController implements Handler {
  ApplicationContext applicationContext;

  public LogoutActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    context.sessionAttribute("user_id", null);
    context.redirect("/login");
  }

}
