package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationRoleRegistrationViewController implements Handler {

  ApplicationContext applicationContext;

  public AuthorizationRoleRegistrationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    if (this.applicationContext.currentUser() == null) {
      context.redirect("/login");
    } else {
      Map<String, Object> model = new HashMap<>();
      model.put("users", this.applicationContext.userManagementSystem().users());
      model.put("title", "Registro de Rol de Autorizacion");
      context.render("authorization-role-registration.hbs", model);
    }
  }
}
