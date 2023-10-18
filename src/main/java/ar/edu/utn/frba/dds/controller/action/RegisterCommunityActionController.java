package ar.edu.utn.frba.dds.controller.action;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;

public class RegisterCommunityActionController implements Handler {
  ApplicationContext applicationContext;

  public RegisterCommunityActionController(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public void handle(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("description", context.formParam("description"));

    this.applicationContext.communityManagementSystem().startManagingCommunityForm(model);
  }
}
