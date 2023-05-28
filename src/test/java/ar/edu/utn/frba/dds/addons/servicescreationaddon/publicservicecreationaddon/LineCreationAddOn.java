package ar.edu.utn.frba.dds.addons.servicescreationaddon.publicservicecreationaddon;

import ar.edu.utn.frba.dds.publicservice.Line;

public class LineCreationAddOn {
  public Line subwayHLine() {
    Line line = new Line();
    line.setName("SUBTE H");
    return line;
  }
}
