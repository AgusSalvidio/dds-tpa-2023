package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class ServiceRegistrationViewController extends Controller implements Handler {

  ApplicationContext applicationContext;

  public ServiceRegistrationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("services", this.loggedUser(context));
    model.put("title", "Registro de Servicios");
    context.render("services/service-registration.hbs", model);
  }
}
