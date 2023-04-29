package ar.edu.utn.frba.dds.publicservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

public class TransportLine {
  @Setter
  @Getter
  public Line line;
  @Setter
  @Getter
  public TransportType type;
  @Setter
  @Getter
  public Station departure;
  @Setter
  @Getter
  public Station arrival;
  public List<Station> stations;
  @Setter
  @Getter
  public Direction direction;

  public TransportLine() {
    this.stations = new ArrayList();
  }

  public void addNewStation(Station newStation) {
    this.stations.add(newStation);
  }

  public List<Station> getStations() {
    return this.stations.stream().collect(Collectors.toList());
  }
}
