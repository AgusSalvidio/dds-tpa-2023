package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistrationViewController implements Handler {

  ApplicationContext applicationContext;

  public ServiceRegistrationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) {
    if (this.applicationContext.currentUser() == null) {
      context.redirect("/login");
    } else {
      Map<String, Object> model = new HashMap<>();
      model.put("services", this.applicationContext.serviceManagementSystem().services());
      model.put("title", "Registro de Servicios");
      context.render("service-registration.hbs", model);
    }
  }
}
