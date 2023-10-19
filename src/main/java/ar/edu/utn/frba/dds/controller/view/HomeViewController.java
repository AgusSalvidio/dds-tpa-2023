package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class HomeViewController extends Controller {

  ApplicationContext applicationContext;

  public HomeViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    /* For debugging porpuses -asalvidio
    if (context.sessionAttribute("user_id") == null) {
      System.out.println("user_id es null");
    } else {
      System.out.println(context.sessionAttribute("user_id").toString());
    }*/

    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Inicio");
    context.render("home.hbs", model);

  }
}

