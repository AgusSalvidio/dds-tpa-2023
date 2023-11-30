package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class RankingViewController {
  ApplicationContext applicationContext;

  public RankingViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void index(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Ranking de Incidentes");
    context.render("rankings/rankings.hbs", model);
  }

  public void cfn(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Mayor grado de impacto de las problemáticas");
    context.render("rankings/cfn.hbs", model);
  }

  public void impactDegree(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("title", "Mayor grado de impacto de las problemáticas");
    //TODO
    context.render("rankings/impact-degree.hbs", model);
  }

}
