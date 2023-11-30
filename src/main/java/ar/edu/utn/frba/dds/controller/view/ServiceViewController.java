package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.service.ServiceType;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.Arrays;
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
    model.put("title", "Servicios");
    context.render("services/services.hbs", model);
  }

  public void create(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Registro de Servicios");
    model.put("servicestype", Arrays.stream(ServiceType.values()).toList());
    context.render("services/service-registration.hbs", model);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));
    model.put("state-name", context.formParam("state-name"));
    model.put("state-description", context.formParam("state-description"));
    model.put("servicetype", context.formParam("servicetype"));

    this.applicationContext.serviceManagementSystem().startManagingServiceFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/services");
  }


}
