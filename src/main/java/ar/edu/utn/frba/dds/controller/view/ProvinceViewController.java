package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.location.Province;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class ProvinceViewController {

  ApplicationContext applicationContext;
  String pageTitle = "ABM de Provincias";
  String actionString = "/province";
  String listPage = "parameters/parameters.hbs";
  String unitPage = "parameters/parameters-registration.hbs";

  String fileName = "province.csv";

  public ProvinceViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", pageTitle);
    model.put("action", actionString);
    model.put("object-list",
        this.applicationContext.provinceManagementSystem().objectList(Province.class.getName()));
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
    Province toEdit = this.applicationContext.provinceManagementSystem().provinceById(id);
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
    Province toUpdate = this.applicationContext.provinceManagementSystem().provinceById(id);
    model.put("name", context.formParam("name"));
    this.applicationContext.provinceManagementSystem().updateFromModel(toUpdate, model);
    context.redirect(actionString);
  }

  public void save(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("name", context.formParam("name"));
    this.applicationContext.provinceManagementSystem().startManaging(model);
    context.status(HttpStatus.CREATED);
    context.redirect(actionString);
  }

  public void delete(Context context) throws Exception {
    Integer id = Integer.parseInt(context.pathParam("id"));
    Province toDelete = this.applicationContext.provinceManagementSystem().provinceById(id);
    this.applicationContext.provinceManagementSystem().stopManaging(toDelete);
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
