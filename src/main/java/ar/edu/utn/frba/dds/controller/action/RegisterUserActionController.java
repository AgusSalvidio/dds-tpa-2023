package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class RegisterUserActionController implements Handler {

  ApplicationContext applicationContext;

  public RegisterUserActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("lastname", context.formParam("lastname"));
    model.put("email", context.formParam("email"));
    model.put("telephone", context.formParam("telephone"));
    model.put("notificationmean", context.formParam("notificationmean"));
    model.put("username", context.formParam("username"));
    model.put("password", context.formParam("password"));

    this.applicationContext.userManagementSystem().startManagingUserFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/users");
  }

}
