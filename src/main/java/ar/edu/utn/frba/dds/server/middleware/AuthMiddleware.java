package ar.edu.utn.frba.dds.server.middleware;

import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;

public class AuthMiddleware {
  public static void apply(JavalinConfig config) {
    config.accessManager(((handler, context, routeRoles) -> {

    }));
  }

}
