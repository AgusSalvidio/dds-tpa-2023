package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.user.User;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;

public abstract class Controller implements WithSimplePersistenceUnit {

  protected User loggedUser(Context context) {
    if (context.sessionAttribute("user_id") == null) {
      return null;
    } else {
      Integer userId = context.sessionAttribute("user_id");
      return entityManager()
          .find(User.class, userId);
    }

  }
}
