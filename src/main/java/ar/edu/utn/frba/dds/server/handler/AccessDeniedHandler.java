package ar.edu.utn.frba.dds.server.handler;

import ar.edu.utn.frba.dds.server.exception.AccessDeniedException;
import io.javalin.Javalin;

public class AccessDeniedHandler implements Handler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(AccessDeniedException.class, (event, context) -> {
      context.redirect("/login");
    });
  }
}
