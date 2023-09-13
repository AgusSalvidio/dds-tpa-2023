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

  /*public User currentUser() {
    return this.applicationContext.currentUser();
  }*/

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", applicationContext);
    context.render("administration.hbs", model);
  }
}
