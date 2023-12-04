package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.incident.IncidentPerCommunity;
import ar.edu.utn.frba.dds.location.Province;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentViewController {

  ApplicationContext applicationContext;
  String pageTitle = "Registro de Incidentes";
  String actionString = "/incidents";
  String listPage = "incidents/incidents.hbs";
  String unitPage = "incidents/incidents-registration.hbs";
  String fileName = "incidents.csv";

  public IncidentViewController(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list",
            this.applicationContext.incidentManagementSystem().incidents());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    model.put("entities",
            this.applicationContext.entityManagementSystem().entities());
    model.put("establishments",
            this.applicationContext.establishmentManagementSystem().establishments());
    model.put("services",
            this.applicationContext.serviceManagementSystem().services());
    model.put("communities",
            this.applicationContext.communityManagementSystem().communities());
    context.render(unitPage, model);
    context.render("incidents/incident-registration.hbs", model);
  }

  public void save(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("entity", context.formParam("entity"));
    model.put("establishment", context.formParam("establishment"));
    model.put("service", context.formParam("service"));
    model.put("reportDateTime", LocalDateTime.now());
    model.put("observations", context.formParam("observations"));
    model.put("user", this.applicationContext.loggedUser(context));
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

  public void filter(Context context) throws Exception {

    String state = context.formParam("states");

    if (state != null) {
      List<IncidentPerCommunity> incidentsPerCommunity = this.applicationContext
          .incidentPerCommunityManagementSystem().incidentsPerCommunityFilteredBy(state);
      Map<String, Object> model = new HashMap<>();

      User loggedUser = this.applicationContext.loggedUser(context);

      model.put("incidents", incidentsPerCommunity);
      model.put("user", loggedUser);
      model.put("title", "Administrar Incidentes");
      context.render("incidents/incidents.hbs", model);
    } else {
      context.redirect("/incidents");
    }
  }

}
