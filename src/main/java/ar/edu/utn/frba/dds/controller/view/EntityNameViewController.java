package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class EntityNameViewController {
  ApplicationContext applicationContext;
  String pageTitle = "ABM de Nombre de Entidades";
  String actionString = "/entity-name";
  String listPage = "parameters/parameters.hbs";
  String unitPage = "parameters/parameters-registration.hbs";

  public EntityNameViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list",
        this.applicationContext.entityNameManagementSystem().entityNames());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    context.render(unitPage, model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    EntityName entityNameToEdit =
        this.applicationContext.entityNameManagementSystem().entityNameIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", entityNameToEdit);
    model.put("buttonActionLabel", "Editar");
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    EntityName entityNameToUpdate =
        this.applicationContext.entityNameManagementSystem().entityNameIdentifiedBy(id);
    model.put("name", context.formParam("name"));
    this.applicationContext
        .entityNameManagementSystem().updateEntityNameFrom(entityNameToUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    this.applicationContext.entityNameManagementSystem().startEntityNameFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    EntityName entityNameToDelete =
        this.applicationContext.entityNameManagementSystem().entityNameIdentifiedBy(id);
    this.applicationContext.entityNameManagementSystem().stopManagingEntityName(entityNameToDelete);
    context.redirect(actionString);
  }

}
