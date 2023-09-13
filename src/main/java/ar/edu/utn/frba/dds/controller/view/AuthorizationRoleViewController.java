package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationRoleViewController implements Handler {

  ApplicationContext applicationContext;

  public AuthorizationRoleViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("roles", this.applicationContext.authorizationRoleManagementSystem().roles());
    context.render("authorization-roles.hbs", model);
  }
}
