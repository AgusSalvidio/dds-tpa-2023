package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class RegisterEntityActionController implements Handler {
  ApplicationContext applicationContext;

  public RegisterEntityActionController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("departure", context.formParam("departure"));
    model.put("arrival", context.formParam("arrival"));
    model.put("direction", context.formParam("direction"));

    this.applicationContext.entityManagementSystem().startManagingEntityForm(model);
  }
}
