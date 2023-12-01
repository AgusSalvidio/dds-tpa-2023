package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.service.Service;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class ServiceViewController extends Controller {

  ApplicationContext applicationContext;

  public ServiceViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("services", this.applicationContext.serviceManagementSystem().services());
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Administrar Servicios");
    context.render("services/services.hbs", model);
  }

  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Registro de Servicios");
    model.put("states",
        this.applicationContext.serviceManagementSystem().states());
    model.put("buttonActionLabel", "Registrar");

    context.render("services/service-registration.hbs", model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();

    Integer id = Integer.parseInt(context.pathParam("id"));

    Service serviceToEdit =
        this.applicationContext.serviceManagementSystem().serviceIdentifiedBy(id);

    model.put("registered_service", serviceToEdit);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Editar Servicio");
    model.put("states",
        this.applicationContext.serviceManagementSystem().states());
    model.put("buttonActionLabel", "Editar");

    context.render("services/service-registration.hbs", model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));
    model.put("state", context.formParam("state"));

    Integer id = Integer.parseInt(context.pathParam("id"));
    Service serviceToUpdate =
        this.applicationContext.serviceManagementSystem().serviceIdentifiedBy(id);

    this.applicationContext.serviceManagementSystem().updateServiceFrom(serviceToUpdate, model);
    context.redirect("/services");
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));
    model.put("serviceType", context.formParam("serviceType"));
    model.put("state", context.formParam("state"));

    this.applicationContext.serviceManagementSystem().startManagingServiceFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/services");
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));

    Service serviceToDelete = this.applicationContext
        .serviceManagementSystem().serviceIdentifiedBy(id);

    this.applicationContext.serviceManagementSystem().stopManagingService(serviceToDelete);
    context.redirect("/services");
  }
}
