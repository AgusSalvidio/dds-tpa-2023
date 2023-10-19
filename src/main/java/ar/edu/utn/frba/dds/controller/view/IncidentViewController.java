package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.service.Service;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class IncidentViewController extends Controller {
  ApplicationContext applicationContext;

  public IncidentViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("incidents", this.applicationContext.incidentManagementSystem().incidents());
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Incidentes");
    context.render("incidents/incidents.hbs", model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();

    model.put("user", this.applicationContext.loggedUser(context));
    model.put("registered_incident", null);
    model.put("services", this.applicationContext.serviceManagementSystem().services());
    model.put("title", "Registro de Incidente");
    context.render("incidents/incident-registration.hbs", model);
  }

  public void save(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();

    System.out.println("lo que hay en el formParam");
    System.out.println(context.formParamMap());

    model.put("service", context.formParam("services"));
    model.put("datetime", LocalDateTime.now());
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("observations", context.formParam("observations"));

    this.applicationContext.incidentManagementSystem().startManagingIncidentFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/incidents");
  }

}
