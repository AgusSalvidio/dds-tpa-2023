package ar.edu.utn.frba.dds;

import java.io.IOException;
import java.util.function.Consumer;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;

public class mainApp {

  public static void main(String[] args) {

    //initializeApplicationContext();
    //initTemplateEngine();

    Integer port = Integer.parseInt(System.getProperty("port", "8080"));
    //Javalin app = Javalin.create(config()).start(port);
    Javalin app = Javalin.create().start(port);
    app.get("/", context -> context.result("Hola Mundo"));

  }

  private static void initTemplateEngine() {
    JavalinRenderer.register(
        (path, model, context) -> { // This renders the template
          Handlebars handlebars = new Handlebars();
          Template template = null;
          try {
            template = handlebars.compile("templates/" + path.replace(".hbs", ""));
            return template.apply(model);
          } catch (IOException e) {
            //
            e.printStackTrace();
            context.status(HttpStatus.NOT_FOUND);
            return "No se encuentra la página indicada...";
          }
        }, ".hbs" // Template file extension
    );
  }

  private static Consumer<JavalinConfig> config() {
    return config -> {
      config.staticFiles.add(staticFiles -> {
        staticFiles.hostedPath = "/";
        staticFiles.directory = "/public";
      });
    };
  }

}