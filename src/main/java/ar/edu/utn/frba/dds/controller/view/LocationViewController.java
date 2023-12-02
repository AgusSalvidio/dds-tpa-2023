package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.location.Location;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class LocationViewController {

  ApplicationContext applicationContext;
  String pageTitle = "ABM de Direcciones";
  String actionString = "/location";
  String listPage = "parameters/parameters.hbs";
  String unitPage = "parameters/parameters-registration.hbs";

  String fileName = "location.csv";

  public LocationViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list",
        this.applicationContext.locationManagementSystem().objectList(Location.class.getName()));
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
    Location toEdit = this.applicationContext.locationManagementSystem().locationById(id);
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("registered_object", toEdit);
    model.put("buttonActionLabel", "Editar");
    context.render(unitPage, model);
  }

  public void update(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    Integer id = Integer.parseInt(context.pathParam("id"));
    Location toUpdate = this.applicationContext.locationManagementSystem().locationById(id);
    model.put("name", context.formParam("name"));
    this.applicationContext.locationManagementSystem().updateFromModel(toUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    this.applicationContext.locationManagementSystem().startManaging(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    Location toDelete = this.applicationContext.locationManagementSystem().locationById(id);
    this.applicationContext.municipalityManagementSystem().stopManaging(toDelete);
    context.redirect(actionString);
  }

  /*
  public void save_massive(Context context) throws Exception {

    context.uploadedFiles("files").forEach(uploadedFile ->
        FileUtil.streamToFile(uploadedFile.content(), "./src/main/resources/upload/" + fileName));

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

