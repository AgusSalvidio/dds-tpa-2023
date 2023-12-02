package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.service.Service;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class EntityViewController extends Controller {

  ApplicationContext applicationContext;

  String pageTitle = "ABM de Entidades";
  String actionString = "/entities";
  String listPage = "entities/entities.hbs";
  String unitPage = "entities/entities-registration.hbs";
  //String fileName = "entity.csv";

  public EntityViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list", this.applicationContext.entityManagementSystem().entities());
    context.render(listPage, model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", null);
    model.put("buttonActionLabel", "Registrar");
    model.put("names",
        this.applicationContext.entityNameManagementSystem().entityNames());
    model.put("types",
        this.applicationContext.entityTypeManagementSystem().entityTypes());
    model.put("establishments",
        this.applicationContext.establishmentManagementSystem().establishments());
    context.render(unitPage, model);
  }

  public void edit(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Entity entityToEdit =
        this.applicationContext.entityManagementSystem().entityIdentifiedBy(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", entityToEdit);
    model.put("buttonActionLabel", "Editar");
    model.put("names",
        this.applicationContext.entityNameManagementSystem().entityNames());
    model.put("types",
        this.applicationContext.entityTypeManagementSystem().entityTypes());
    model.put("establishments",
        this.applicationContext.establishmentManagementSystem().establishments());
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Entity entityToUpdate =
        this.applicationContext.entityManagementSystem().entityIdentifiedBy(id);
    assignParameters(context, model, entityToUpdate.getClass().getSimpleName());
    this.applicationContext.entityManagementSystem().updateEntityFrom(entityToUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("entity", context.formParam("entity"));
    String entity = model.get("entity").toString();
    System.out.println(entity);
    assignParameters(context, model, entity);
    this.applicationContext.entityManagementSystem().startManagingEntityFrom(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    Entity entityToDelete = this.applicationContext
        .entityManagementSystem().entityIdentifiedBy(id);
    this.applicationContext.entityManagementSystem().stopManagingEntity(entityToDelete);
    context.redirect(actionString);
  }

  private void assignParameters(Context context, Map<String, Object> model, String entity) {
    model.put("name", context.formParam("name"));
    model.put("type", context.formParam("type"));
    if (entity.equals("transportLine") || entity.equals("TransportLine")) {
      model.put("departure", context.formParam("departure"));
      model.put("arrival", context.formParam("arrival"));
      model.put("direction", context.formParam("direction"));
    }
  }

  /*
  public void save_massive(Context context) throws Exception {

    context.uploadedFiles("files").forEach(uploadedFile ->
            FileUtil.streamToFile(uploadedFile.content(),
            "./src/main/resources/upload/" + fileName));

    String filePath = String.format("./src/main/resources/upload/%s", fileName);

    DataFile dataFile = new FileDelimited("EntityType");
    //Set Source Structure
    dataFile.setFilePath(filePath);
    dataFile.setRowDelimiter("CrLf");
    dataFile.setColDelimiter(";");
    dataFile.setFirstRowHasColumnNames(false);
    //Set Source Fields
    dataFile.addField(new FieldString(0, "Type", 255));
    dataFile.addField(new FieldString(1, "Name", 255));
  }
  */
}
