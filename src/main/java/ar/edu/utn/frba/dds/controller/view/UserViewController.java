package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.authorizationrole.AuthorizationRole;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserViewController extends Controller {

  ApplicationContext applicationContext;
  String pageTitle = "ABM de Usuarios";
  String actionString = "/users";
  String listPage = "users/users.hbs";
  String unitPage = "users/users-registration.hbs";


  public UserViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list", this.applicationContext.userManagementSystem().users());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    model.put("authorizationRoles", Arrays.stream(AuthorizationRole.values()).toList());
    context.render(unitPage, model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    User userToEdit = this.applicationContext.userManagementSystem().userIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", userToEdit);
    model.put("buttonActionLabel", "Editar");
    model.put("authorizationRoles", Arrays.stream(AuthorizationRole.values()).toList());
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    User userToUpdate = this.applicationContext.userManagementSystem().userIdentifiedBy(id);
    assignParameters(context, model);
    this.applicationContext.userManagementSystem().updateUserFrom(userToUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    assignParameters(context, model);
    this.applicationContext.userManagementSystem().startManagingUserFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    User userToDelete = this.applicationContext.userManagementSystem().userIdentifiedBy(id);
    this.applicationContext.userManagementSystem().stopManagingUser(userToDelete);
    context.redirect(actionString);
  }

  private void assignParameters(Context context, Map<String, Object> model) {
    model.put("name", context.formParam("name"));
    model.put("lastname", context.formParam("lastname"));
    model.put("email", context.formParam("email"));
    model.put("telephone", context.formParam("telephone"));
    model.put("username", context.formParam("username"));
    model.put("password", context.formParam("password"));
    model.put("notificationMean", context.formParam("notificationMean"));
    model.put("authorizationRole", context.formParam("authorizationRole"));
  }
}
