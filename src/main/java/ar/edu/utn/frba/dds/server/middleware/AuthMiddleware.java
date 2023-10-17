package ar.edu.utn.frba.dds.server.middleware;

import ar.edu.utn.frba.dds.server.exception.AccessDeniedException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class AuthMiddleware {


  public static void apply(JavalinConfig config) {
    String[] allowedPaths = new String[]{"/login", "/register-login"};
    config.accessManager(((handler, context, routeRoles) -> {
      System.out.println(context.path());
      if (context.sessionAttribute("user_id") == null && !Arrays.asList(allowedPaths).contains(context.path())) {
        throw new AccessDeniedException();
      } else {
        handler.handle(context);
      }

    }));
  }

}
