package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class RegisterIncidentPerCommunityActionController implements Handler {
  ApplicationContext applicationContext;

  public RegisterIncidentPerCommunityActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("incident", context.formParam("incident"));
    model.put("community", context.formParam("community"));

    this.applicationContext.incidentPerCommunityManagementSystem()
        .startManagingIncidentPerCommunityForm(model);
  }
}
