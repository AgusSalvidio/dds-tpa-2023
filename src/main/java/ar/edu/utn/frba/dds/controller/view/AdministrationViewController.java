package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class AdministrationViewController extends Controller {

  ApplicationContext applicationContext;

  public AdministrationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Administración");
    context.render("administration/administration.hbs", model);
  }
}
