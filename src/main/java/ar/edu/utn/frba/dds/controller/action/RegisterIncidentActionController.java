package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class RegisterIncidentActionController implements Handler {
  ApplicationContext applicationContext;

  public RegisterIncidentActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("service", context.formParam("service"));
    model.put("dateTime", context.formParam("dateTime"));
    model.put("observations", context.formParam("observations"));
    model.put("user", context.formParam("user"));

    this.applicationContext.incidentManagementSystem().startManagingIncidentForm(model);
  }
}
