package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.helper.CustomHelper;
import ar.edu.utn.frba.dds.server.middleware.AuthMiddleware;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;
import java.io.IOException;
import java.util.function.Consumer;

public class Server {
  private static Javalin app = null;

  public static Javalin app() {
    if (app == null)
      throw new RuntimeException("App no inicializada");
    return app;
  }

  public static void initialize() {
    if (app == null) {
      Integer port = Integer.parseInt(System.getProperty("port", "8080"));
      app = Javalin.create(config()).start(port);
      initializeTemplateEngine();
      Router.initialize();
    }
  }

  private static void registerCustomHelpersTo(Handlebars handlebars) {

    CustomHelper customHelper = CustomHelper.workingWith(handlebars);
    customHelper.initializeHelpers();

  }

  private static void initializeTemplateEngine() {
    JavalinRenderer.register(
        (path, model, context) -> { // This renders the template
          Handlebars handlebars = new Handlebars();
          registerCustomHelpersTo(handlebars);
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
      AuthMiddleware.apply(config);
    };
  }

}
