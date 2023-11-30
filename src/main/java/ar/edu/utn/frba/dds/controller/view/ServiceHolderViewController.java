package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.datafile.DataFile;
import ar.edu.utn.frba.dds.datafile.Field;
import ar.edu.utn.frba.dds.datafile.FieldString;
import ar.edu.utn.frba.dds.datafile.FileDelimited;
import ar.edu.utn.frba.dds.serviceholder.Company;
import ar.edu.utn.frba.dds.serviceholder.GovermentDepartment;
import ar.edu.utn.frba.dds.user.User;
import io.javalin.http.Context;
import io.javalin.util.FileUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceHolderViewController {

  ApplicationContext applicationContext;

  public ServiceHolderViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("serviceHolders",
        this.applicationContext.serviceHolderManagementSystem().serviceHolders());
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Entidades");
    context.render("service-holders/service-holders.hbs", model);
  }

  public void create(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();

    User loggedUser = this.applicationContext.loggedUser(context);

    model.put("user", loggedUser);
    model.put("registered_service_holder", null);
    model.put("title", "Registro de Entidad");
    model.put("buttonActionLabel", "Registrar");

    context.render("service-holders/service-holder-registration.hbs", model);
  }

  public void save(Context context) throws Exception {

    String fileName = "ServiceHolders.csv";

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
    //Read File
    dataFile.openFile();
    List<Field> row;

    //Hardcoded because its not documented how to know lines amount -asalvidio
    for (int i = 0; i < 2; i++) {

      row = dataFile.getRow();

      switch (row.get(0).getStringValue()) {
        case "Company":
          Company company = Company.composedOf(row.get(1).getStringValue(),
              row.get(2).getStringValue());
          this.applicationContext.serviceHolderManagementSystem()
              .startManagingServiceHolder(company);
          break;
        case "GovermentDepartment":
          GovermentDepartment govermentDepartment = GovermentDepartment.composedOf(
              row.get(1).getStringValue(),
              row.get(2).getStringValue());
          this.applicationContext.serviceHolderManagementSystem()
              .startManagingServiceHolder(govermentDepartment);
          break;
        default:
          break;
      }

    }
    dataFile.close();

    context.redirect("/service-holders");
  }
}
