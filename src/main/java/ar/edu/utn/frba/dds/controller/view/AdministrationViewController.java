package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class AdministrationViewController extends Controller implements Handler {

  ApplicationContext applicationContext;

  public AdministrationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }


  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.loggedUser(context));
    model.put("title", "Administración");
    context.render("administration/administration.hbs", model);
  }
}
