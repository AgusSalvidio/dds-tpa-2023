package ar.edu.utn.frba.dds.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class HomeViewController implements Handler {

  public HomeViewController() {
    super();
  }

  @Override
  public void handle(Context context) throws Exception {
    context.render("home.hbs");
  }
}
