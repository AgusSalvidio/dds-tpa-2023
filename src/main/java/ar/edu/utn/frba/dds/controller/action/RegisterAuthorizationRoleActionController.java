package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class RegisterAuthorizationRoleActionController implements Handler {

  ApplicationContext applicationContext;

  public RegisterAuthorizationRoleActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("role", context.formParam("role"));
    model.put("user", context.formParam("users"));

    this.applicationContext.authorizationRoleManagementSystem()
        .startManagingAuthorizationRoleFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/authorization-roles");
  }

}
