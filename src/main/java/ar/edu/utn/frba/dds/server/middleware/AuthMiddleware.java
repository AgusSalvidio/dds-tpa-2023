package ar.edu.utn.frba.dds.server.middleware;

import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.server.exception.AccessDeniedException;
import ar.edu.utn.frba.dds.server.exception.AccessForbiddenException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class AuthMiddleware {

  public static void apply(JavalinConfig config) {
    String[] allowedPaths = new String[]{"/login", "/register-login", "/logout"};
    config.accessManager(((handler, context, routeRoles) -> {
      System.out.println(context.path());

      if (context.sessionAttribute("user_id") == null
          && !Arrays.asList(allowedPaths).contains(context.path())) {
        throw new AccessDeniedException();
      } else {

        AuthorizationRole role = authorizationRoleFrom(context);

        if (routeRoles.isEmpty() || routeRoles.contains(role)) {
          handler.handle(context);
        } else {
          throw new AccessForbiddenException();
        }
      }

    }));
  }

  private static AuthorizationRole authorizationRoleFrom(Context context) {
    return context.sessionAttribute("user_authorization_role") != null
        ? AuthorizationRole.valueOf(context.sessionAttribute("user_authorization_role")) : null;
  }

}
