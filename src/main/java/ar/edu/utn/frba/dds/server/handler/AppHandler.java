package ar.edu.utn.frba.dds.server.handler;

import io.javalin.Javalin;
import java.util.Arrays;

public class AppHandler {
  private Handler[] handlers = new Handler[]{
      new AccessDeniedHandler(),
  };

  public static void applyHandlers(Javalin app) {
    Arrays.stream(new AppHandler().handlers).toList().forEach(handler -> handler.setHandle(app));
  }

}
