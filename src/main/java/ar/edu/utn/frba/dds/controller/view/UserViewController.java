package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class UserViewController extends Controller {

  ApplicationContext applicationContext;

  public UserViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("users", this.applicationContext.userManagementSystem().users());
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Usuarios");
    context.render("users/users.hbs", model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("registered_user", null);
    model.put("title", "Registro de Usuario");
    context.render("users/user-registration.hbs", model);
  }

  public void save(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("lastname", context.formParam("lastname"));
    model.put("email", context.formParam("email"));
    model.put("telephone", context.formParam("telephone"));
    model.put("notificationmean", context.formParam("notificationmean"));
    model.put("username", context.formParam("username"));
    model.put("password", context.formParam("password"));

    this.applicationContext.userManagementSystem().startManagingUserFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect("/users");
  }

  public void delete(Context context) throws Exception {

    Integer id = Integer.parseInt(context.pathParam("id"));

    User userToDelete = this.applicationContext.userManagementSystem().userIdentifiedBy(id);

    this.applicationContext.userManagementSystem().stopManaging(userToDelete);
    context.redirect("/users");
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();

    Integer id = Integer.parseInt(context.pathParam("id"));

    User userToEdit = this.applicationContext.userManagementSystem().userIdentifiedBy(id);

    model.put("registered_user", userToEdit);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Editar");

    context.render("users/user-registration.hbs", model);
  }

  public void update(Context context) throws Exception {

    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    model.put("lastname", context.formParam("lastname"));
    model.put("email", context.formParam("email"));
    model.put("telephone", context.formParam("telephone"));
    model.put("notificationmean", context.formParam("notificationmean"));
    model.put("username", context.formParam("username"));
    model.put("password", context.formParam("password"));

    Integer id = Integer.parseInt(context.pathParam("id"));

    User userToUpdate = this.applicationContext.userManagementSystem().userIdentifiedBy(id);

    this.applicationContext.userManagementSystem().updateUserFrom(userToUpdate, model);
    context.redirect("/users");
  }

}

