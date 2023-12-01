package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.service.Service;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class ServiceViewController extends Controller {

  ApplicationContext applicationContext;
  String pageTitle = "ABM de Servicios";
  String actionString = "/services";
  String listPage = "services/services.hbs";
  String unitPage = "services/services-registration.hbs";

  public ServiceViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list", this.applicationContext.serviceManagementSystem().services());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    model.put("states",
        this.applicationContext.serviceManagementSystem().states());
    context.render(unitPage, model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Service serviceToEdit =
        this.applicationContext.serviceManagementSystem().serviceIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", serviceToEdit);
    model.put("buttonActionLabel", "Editar");
    model.put("states",
        this.applicationContext.serviceManagementSystem().states());
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Service serviceToUpdate =
        this.applicationContext.serviceManagementSystem().serviceIdentifiedBy(id);
    assignParameters(context, model);
    this.applicationContext.serviceManagementSystem().updateServiceFrom(serviceToUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    assignParameters(context, model);
    model.put("serviceType", context.formParam("serviceType"));
    this.applicationContext.serviceManagementSystem().startManagingServiceFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    Service serviceToDelete = this.applicationContext
        .serviceManagementSystem().serviceIdentifiedBy(id);
    this.applicationContext.serviceManagementSystem().stopManagingService(serviceToDelete);
    context.redirect(actionString);
  }

  private void assignParameters(Context context, Map<String, Object> model) {
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));
    model.put("state", context.formParam("state"));
  }
}
