package ar.edu.utn.frba.dds.server.handler;

import ar.edu.utn.frba.dds.server.exception.AccessForbiddenException;
import io.javalin.Javalin;

public class AccessForbiddenHandler implements Handler {
  @Override
  public void setHandle(Javalin app) {
    app.exception(AccessForbiddenException.class, (event, context) -> {
      context.redirect("/");
    });
  }
}
