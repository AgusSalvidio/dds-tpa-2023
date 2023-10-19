package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.community.Member;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.service.Service;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.user.User;
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

    User loggedUser = this.applicationContext.loggedUser(context);

    model.put("incidents",
        this.applicationContext.incidentPerCommunityManagementSystem().incidentsPerCommunity());
    model.put("user", loggedUser);
    model.put("title", "Administrar Incidentes");
    context.render("incidents/incidents.hbs", model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();

    User loggedUser = this.applicationContext.loggedUser(context);

    model.put("user", loggedUser);
    model.put("registered_incident", null);
    model.put("services",
        this.applicationContext.serviceManagementSystem().services());
    model.put("communities",
        this.applicationContext.communityManagementSystem().communities());
    model.put("title", "Registro de Incidente");
    model.put("buttonActionLabel", "Registrar");

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
    model.put("community", context.formParam("communities"));

    this.applicationContext.incidentManagementSystem().startManagingIncidentFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/incidents");
  }

  public void close(Context context) throws Exception {

    Integer id = Integer.parseInt(context.pathParam("id"));

    IncidentPerCommunity incidentPerCommunity = this.applicationContext
        .incidentPerCommunityManagementSystem().incidentPerCommunityIdentifiedBy(id);

    this.applicationContext.incidentPerCommunityManagementSystem()
        .closeIncidentPerCommunity(incidentPerCommunity);
    context.redirect("/incidents");
  }

  public void review(Context context) throws Exception {

    Integer id = Integer.parseInt(context.pathParam("id"));

    Map<String, Object> model = new HashMap<>();

    User loggedUser = this.applicationContext.loggedUser(context);
    IncidentPerCommunity incidentPerCommunity = this.applicationContext
        .incidentPerCommunityManagementSystem().incidentPerCommunityIdentifiedBy(id);
    model.put("user", loggedUser);
    model.put("registered_incident", incidentPerCommunity);
    model.put("title", "Revision de Incidente");
    model.put("buttonActionLabel", "Cerrar");
    context.render("incidents/incident-registration.hbs", model);
  }

}
