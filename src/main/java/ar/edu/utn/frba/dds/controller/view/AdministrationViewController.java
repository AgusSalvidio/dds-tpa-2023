package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class AdministrationViewController implements Handler {

  ApplicationContext applicationContext;

  public AdministrationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }


  @Override
  public void handle(Context context) throws Exception {
    if (this.applicationContext.currentUser() == null) {
      context.redirect("/login");
    } else {
      Map<String, Object> model = new HashMap<>();
      model.put("user", this.applicationContext.currentUser());
      model.put("title", "Administración");
      context.render("administration/administration.hbs", model);
    }
  }
}
