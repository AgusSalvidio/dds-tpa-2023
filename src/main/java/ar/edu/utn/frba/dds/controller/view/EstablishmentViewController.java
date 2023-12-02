package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.establishment.Establishment;
import ar.edu.utn.frba.dds.location.Location;
import ar.edu.utn.frba.dds.service.Service;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class EstablishmentViewController extends Controller {

  ApplicationContext applicationContext;
  String pageTitle = "ABM de Establecimientos";
  String actionString = "/establishments";
  String listPage = "establishments/establishments.hbs";
  String unitPage = "establishments/establishments-registration.hbs";
  String servicePage = "establishments/establishments-add-service.hbs";

  public EstablishmentViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list",
        this.applicationContext.establishmentManagementSystem().establishments());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    model.put("types",
        this.applicationContext.establishmentTypeManagementSystem().establishmentTypes());
    model.put("locations",
            this.applicationContext.locationManagementSystem().objectList(Location.class.getName()));
    model.put("services",
            this.applicationContext.serviceManagementSystem().services());
    context.render(unitPage, model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Establishment establishmentToEdit =
        this.applicationContext.establishmentManagementSystem().establishmentIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", establishmentToEdit);
    model.put("buttonActionLabel", "Editar");
    model.put("types",
        this.applicationContext.establishmentTypeManagementSystem().establishmentTypes());
    model.put("locations",
            this.applicationContext.locationManagementSystem().objectList(Location.class.getName()));
    model.put("services", establishmentToEdit.services );
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Establishment establishmentToUpdate =
        this.applicationContext.establishmentManagementSystem().establishmentIdentifiedBy(id);
    assignParameters(context, model);
    this.applicationContext
        .establishmentManagementSystem().updateEstablishmentFrom(establishmentToUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    assignParameters(context, model);
    this.applicationContext.establishmentManagementSystem().startManagingEstablishmentFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    Establishment establishmentToDelete =
        this.applicationContext.establishmentManagementSystem().establishmentIdentifiedBy(id);
    this.applicationContext
        .establishmentManagementSystem().stopManagingEstablishment(establishmentToDelete);
    context.redirect(actionString);
  }

  public void createservice(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Establishment establishmentToEdit =
            this.applicationContext.establishmentManagementSystem().establishmentIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", establishmentToEdit);
    model.put("buttonActionLabel", "Editar");
    model.put("types",
            this.applicationContext.establishmentTypeManagementSystem().establishmentTypes());
    model.put("locations",
            this.applicationContext.locationManagementSystem().objectList(Location.class.getName()));
    model.put("services",
            this.applicationContext.serviceManagementSystem().services());
    context.render(servicePage, model);
  }

  public void updateservice(Context context) {
    Integer id = Integer.valueOf(context.formParam("id").toString());
    Integer service_id = Integer.valueOf(context.formParam("service").toString());

    Establishment establishmentToUpdate =
            this.applicationContext.establishmentManagementSystem().establishmentIdentifiedBy(id);
    Service service = this.applicationContext.serviceManagementSystem().serviceIdentifiedBy(service_id);
    establishmentToUpdate.addNewService(service);
    this.applicationContext
            .establishmentManagementSystem().updateEstablishmentWith(establishmentToUpdate);
    context.redirect(actionString);
  }

  private void assignParameters(Context context, Map<String, Object> model) {
    model.put("name", context.formParam("name"));
    model.put("type", context.formParam("type"));
  }
}
