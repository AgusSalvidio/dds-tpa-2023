package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class RegisterLoginActionController implements Handler {

  ApplicationContext applicationContext;

  public RegisterLoginActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("username", context.formParam("username"));
    model.put("password", context.formParam("password"));
    this.applicationContext.setCurrentUser();
    context.redirect("/");
  }

}
