package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class RegisterServiceActionController implements Handler {

  ApplicationContext applicationContext;

  public RegisterServiceActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));
    model.put("state-name", context.formParam("state-name"));
    model.put("state-description", context.formParam("state-description"));
    model.put("service-type", context.formParam("service-type"));

    this.applicationContext.serviceManagementSystem().startManagingServiceFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/services");
  }
}
