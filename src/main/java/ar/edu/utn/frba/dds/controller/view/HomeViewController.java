package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class HomeViewController implements Handler {

  ApplicationContext applicationContext;

  public HomeViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public User currentUser() throws Exception {
    //return this.applicationContext.currentUser();
    //FOR TESTING ONLY
    UserDetail userDetail = UserDetail.composedOf("Basura", "Intergalactica", "basuraintergalactica@gmail.com");
    return User.composedOf("BasuraIntergalactica", "theBestPassword", userDetail);
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.currentUser());
    context.render("home.hbs", model);
  }
}
