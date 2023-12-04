package ar.edu.utn.frba.dds.controller.view;

import ar.edu.utn.frba.dds.applicationcontext.ApplicationContext;
import ar.edu.utn.frba.dds.community.Community;
import ar.edu.utn.frba.dds.entity.Entity;
import ar.edu.utn.frba.dds.incident.Incident;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.ServiceIncidentImpactRankingCalculator;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.BodyData;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.EntityData;
import ar.edu.utn.frba.dds.services.incidentimpactrankingcalculator.entities.Result;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingViewController {
  ApplicationContext applicationContext;

  public RankingViewController(ApplicationContext applicationContext) {
    super();
    this.applicationContext = applicationContext;
  }

  public void averageClosingTime(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Mayor Promedio de Tiempo de Cierre");

    List<Incident> incidents = this.applicationContext.incidentManagementSystem().incidents();

    //Crear un mapa donde la clave es el nombre de la entidad y el valor es la sumatoria del timeIncident
    Map<String, Double> sumatoriaPorEntidad = incidents.stream()
        .collect(Collectors.groupingBy(incident -> incident.getEntity().getName().getName(),
            Collectors.summingDouble(Incident::getTimeIncident)));

    //Crear una lista de Resultado con el nombre de la entidad y la sumatoria del timeIncident
    List<Result> results = sumatoriaPorEntidad.entrySet().stream()
        .map(entry -> new Result(entry.getKey(), entry.getValue()))
        .sorted(Comparator.comparingDouble(Result::getImpactLevel).reversed())
        .collect(Collectors.toList());

    model.put("results", results);
    context.render("rankings/rankings.hbs", model);
    /*
    //Imprimir resultados
    results.forEach(result ->
        System.out.println("Entidad: " + result.getName() +
            ", Sumatoria del timeIncident: " + result.getCount()));
     */
  }

  public void greaterAmount(Context context) throws Exception {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Mayor Cantidad de Reportados");

    List<Incident> incidents = this.applicationContext.incidentManagementSystem().incidents();

    //Crear un map donde la clave es el nombre de la entidad y el valor es la cantidad de incidentes
    Map<String, Double> cantidadPorEntidad = incidents.stream()
        .collect(Collectors.groupingBy(incident -> incident.getEntity().getName().getName(),
            Collectors.summingDouble(incident -> 1.0)));

    //Crear una lista de Resultado con el nombre de la entidad y la cantidad de incidentes
    List<Result> results = cantidadPorEntidad.entrySet().stream()
        .map(entry -> new Result(entry.getKey(), entry.getValue()))
        .sorted(Comparator.comparingDouble(Result::getImpactLevel).reversed())
        .collect(Collectors.toList());

    model.put("results", results);
    context.render("rankings/rankings.hbs", model);
    /*
    //Imprimir resultados
    results.forEach(result ->
        System.out.println("Entidad: " + result.getName() +
            ", Cantidad de Incidentes: " + result.getCount()));
    */
  }

  public void cfn(Context context) {
    Map<String, Object> model = new HashMap<>();
    model.put("user", this.applicationContext.loggedUser(context));
    model.put("title", "Mayor grado de impacto de las problemáticas");
    context.render("rankings/cfn.hbs", model);
  }

  public void impactDegree(Context context) throws IOException {
    Map<String, Object> model = new HashMap<>();
    model.put("title", "Mayor grado de impacto de las problemáticas");
    model.put("cfn", context.formParam("cfn"));

    BodyData bodyData = new BodyData();
    bodyData.setCfn(Double.valueOf(model.get("cfn").toString()));

    List<Incident> incidents = this.applicationContext.incidentManagementSystem().incidents();
    List<EntityData> entitiesData = generateEntityDataList(incidents);
    /*
     for (EntityData entityData : entitiesData) {
      System.out.println("Entity: " + entityData.getName());
      System.out.println("Incidents Duration: " + entityData.getIncidentsDuration());
      System.out.println("Incidents Not Resolved: " + entityData.getIncidentsNotResolved());
      System.out.println("Members Affected: " + entityData.getMembersAffected());
      System.out.println("------------------------------");
    }
    */

    for (EntityData entityData : entitiesData) {
      bodyData.addNewEntityData(entityData);
    }

    ServiceIncidentImpactRankingCalculator apiService =
        ServiceIncidentImpactRankingCalculator.instance();

    List<Result> results = apiService.calculateImpactRanking(bodyData);

    model.put("results", results);
    context.render("rankings/rankings.hbs", model);
    /*
    for(Result result:results) {
      System.out.println("name: " + result.getName() + " -> impactLevel: " + result.getImpactLevel());
    }
    */
  }

  public static List<EntityData> generateEntityDataList(List<Incident> incidents) {
    // Map para almacenar información acumulada por entidad
    Map<Entity, EntityData> entityDataMap = new HashMap<>();

    // Procesar cada incidente
    for (Incident incident : incidents) {
      // Obtener la entidad del incidente
      Entity entity = incident.getEntity();

      // Obtener o crear EntityData correspondiente a la entidad
      EntityData entityData = entityDataMap.computeIfAbsent(entity, k -> new EntityData());
      entityData.name = entity.getName().getName();

      // Procesar la duración del incidente cerrado
      if (incident.getIsClosed() && incident.getTimeIncident() != null) {
        if (entityData.incidentsDuration == null) {
          entityData.incidentsDuration = new ArrayList<>();
        }
        entityData.incidentsDuration.add(incident.getTimeIncident());
      }

      // Incrementar la cantidad de incidentes no resueltos
      if (!incident.getIsClosed()) {
        entityData.incidentsNotResolved++;
      }

      // Sumar los miembros de cada comunidad a la que pertenece la entidad
      if (incident.getCommunities() != null) {
        for (Community community : incident.getCommunities()) {
          if (community.getMembers() != null) {
            entityData.membersAffected += community.getMembers().size();
          }
        }
      }
    }
    // Convertir el map a una lista de EntityData
    return new ArrayList<>(entityDataMap.values());
  }
}