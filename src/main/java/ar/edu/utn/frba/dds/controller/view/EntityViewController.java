package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.datafile.DataFile;
import ar.edu.utn.frba.dds.datafile.Field;
import ar.edu.utn.frba.dds.datafile.FieldNumber;
import ar.edu.utn.frba.dds.datafile.FieldString;
import ar.edu.utn.frba.dds.datafile.FileDelimited;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.entity.TransportLine;
import ar.edu.utn.frba.dds.entity.Direction;
import ar.edu.utn.frba.dds.entity.EntityName;
import ar.edu.utn.frba.dds.entity.EntityType;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.util.FileUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityViewController {

    ApplicationContext applicationContext;

    public EntityViewController(ApplicationContext applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    public void index(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("entities",
                this.applicationContext.entityManagementSystem().entities());
        model.put("user", this.applicationContext.loggedUser(context));
        model.put("title", "Entidades");
        context.render("entities/entities.hbs", model);
    }

    public void create(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User loggedUser = this.applicationContext.loggedUser(context);

        model.put("user", loggedUser);
        model.put("registered_entity", null);
        model.put("title", "Registro de Entidad");
        model.put("buttonActionLabel", "Registrar");

        context.render("entities/entity-registration.hbs", model);
    }

    public void save(Context context) throws Exception {

        String fileName = "entities.csv";

        context.uploadedFiles("files").forEach(uploadedFile ->
                FileUtil.streamToFile(uploadedFile.content(), "./src/main/resources/upload/" + fileName));

        String filePath = String.format("./src/main/resources/upload/%s", fileName);

        DataFile dataFile = new FileDelimited("Entity");
        //Set Source Structure
        dataFile.setFilePath(filePath);
        dataFile.setRowDelimiter("CrLf");
        dataFile.setColDelimiter(";");
        dataFile.setFirstRowHasColumnNames(false);
        //Set Source Fields
        dataFile.addField(new FieldString(0, "Type", 255));
        dataFile.addField(new FieldString(1, "Name", 255));
        dataFile.addField(new FieldString(2, "Description", 255));
    }
}
