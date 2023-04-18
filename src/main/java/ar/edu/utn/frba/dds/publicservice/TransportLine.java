package ar.edu.utn.frba.dds.publicservice;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class TransportLine {
  @Setter
  @Getter
  private String name;
  @Setter
  @Getter
  private TransportType type;
  @Setter
  @Getter
  private Station departure;
  @Setter
  @Getter
  private Station arrival;
  @Getter
  private List<Station> stations;
  @Setter
  @Getter
  private Direction direction;

  public TransportLine() {
    this.stations = new ArrayList();
  }

  public void addNewStation(Station newStation) {
    this.stations.add(newStation);
  }
}
