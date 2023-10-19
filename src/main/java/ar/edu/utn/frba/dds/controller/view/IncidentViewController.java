package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class IncidentViewController extends Controller implements Handler {
  ApplicationContext applicationContext;

  public IncidentViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("incidents", this.applicationContext.incidentManagementSystem().incidents());
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Incidentes");
    context.render("incidents/incidents.hbs", model);
  }

}
