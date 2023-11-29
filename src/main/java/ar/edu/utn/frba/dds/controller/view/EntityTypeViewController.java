package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.datafile.DataFile;
import ar.edu.utn.frba.dds.datafile.Field;
import ar.edu.utn.frba.dds.datafile.FieldNumber;
import ar.edu.utn.frba.dds.datafile.FieldString;
import ar.edu.utn.frba.dds.datafile.FileDelimited;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.notification.notificationmean.NotificationType;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.user.UserDetail;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.util.FileUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityTypeViewController {

    ApplicationContext applicationContext;
    String pageTitle = "A/E de Tipo de Entidades";
    String actionString = "entity-type";
    String listPage = "parameters/parameters.hbs";
    String unitPage = "parameters/parameters-registration.hbs";
    String fileName = "entity-type.csv";

    public EntityTypeViewController(ApplicationContext applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    public void index(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("title", pageTitle);
        model.put("user", this.applicationContext.loggedUser(context));
        model.put("action", actionString);
        model.put("object-list", this.applicationContext.entityTypeManagementSystem().objectList(EntityType.class.getName()));
        context.render(listPage, model);
    }

    public void create(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("title", pageTitle);
        model.put("user", this.applicationContext.loggedUser(context));
        model.put("action", actionString);
        model.put("registered_object", null);
        model.put("buttonActionLabel", "Registrar");
        context.render(unitPage, model);
    }

    public void edit(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        EntityType object = this.applicationContext.entityTypeManagementSystem().entityTypeById(Integer.parseInt(context.pathParam("id")));
        model.put("title", pageTitle);
        model.put("user", this.applicationContext.loggedUser(context));
        model.put("action", actionString);
        model.put("registered_object", object);
        model.put("buttonActionLabel", "Editar");
        context.render(unitPage, model);
    }

    public void save(Context context) throws Exception {
        EntityType object = new EntityType();
        object.name = context.formParam("name");
        this.applicationContext.entityTypeManagementSystem().startManaging(object);
        context.status(HttpStatus.CREATED);
        context.redirect(actionString);
    }

    public void update(Context context) throws Exception {
        EntityType object = this.applicationContext.entityTypeManagementSystem().entityTypeById(Integer.parseInt(context.pathParam("id")));
        object.setId(Integer.parseInt(context.pathParam("id")));
        object.name = context.formParam("name");
        this.applicationContext.entityTypeManagementSystem().startManaging(object);
        context.status(HttpStatus.CREATED);
        context.redirect(actionString);
    }

    public void delete(Context context) throws Exception {
        /*
        Integer id = Integer.parseInt(context.pathParam("id"));
        User userToDelete = this.applicationContext.userManagementSystem().userIdentifiedBy(id);
        this.applicationContext.userManagementSystem().stopManaging(userToDelete);
        */

        context.redirect(listPage);
    }

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
}
