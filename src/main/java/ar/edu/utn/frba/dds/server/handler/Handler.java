package ar.edu.utn.frba.dds.server.handler;

import io.javalin.Javalin;

public interface Handler {
  void setHandle(Javalin app);
}
