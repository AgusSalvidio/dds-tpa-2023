package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.datafile.DataFile;
import ar.edu.utn.frba.dds.datafile.Field;
import ar.edu.utn.frba.dds.datafile.FieldNumber;
import ar.edu.utn.frba.dds.datafile.FieldString;
import ar.edu.utn.frba.dds.datafile.FileDelimited;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.util.FileUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityTypeViewController {

    ApplicationContext applicationContext;

    public EntityTypeViewController(ApplicationContext applicationContext) {
        super();
        this.applicationContext = applicationContext;
    }

    public void index(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("entity-types",
                this.applicationContext.entityManagementSystem().entities());
        model.put("user", this.applicationContext.loggedUser(context));
        model.put("title", "TipoEntidades");
        context.render("parameters/entity-type.hbs", model);
    }

    public void create(Context context) throws Exception {
        Map<String, Object> model = new HashMap<>();

        User loggedUser = this.applicationContext.loggedUser(context);

        model.put("user", loggedUser);
        model.put("registered_entity-type", null);
        model.put("title", "Registro de Tipo de Entidades");
        model.put("buttonActionLabel", "Registrar");

        context.render("parameters/entity-type-registration.hbs", model);
    }

    public void save(Context context) throws Exception {

        String fileName = "entity-type.csv";

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
