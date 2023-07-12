package ar.edu.utn.frba.dds.incident;

import ar.edu.utn.frba.dds.addons.usercreationaddon.UserCreationAddOn;
import ar.edu.utn.frba.dds.service.Elevator;
import ar.edu.utn.frba.dds.service.State;
import ar.edu.utn.frba.dds.user.User;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.ElevatorCreationAddOn;
import ar.edu.utn.frba.dds.addons.servicescreationaddon.servicecreationaddon.StateCreationAddOn;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncidentTest {
  private User ibarraneta() throws Exception {
    return new UserCreationAddOn().ibarraneta();
  }

  private Elevator elevator() {
    return new ElevatorCreationAddOn().elevator();
  }

  private LocalDateTime curentDateTime() {
    return LocalDateTime.of(2023, 7, 2, 23, 40);
  }

  @Test
  @DisplayName("Create an incident")
  public void createAnIncidentTest() throws Exception {
    Elevator elevator = this.elevator();
    User user = this.ibarraneta();
    LocalDateTime curentDateTime = this.curentDateTime();
    String observation = "No funciona el ascensor correctamente";

    Incident incident = Incident.composedOf(
        elevator,
        observation,
        curentDateTime,
        user
    );

    Assertions.assertEquals(incident.service(), elevator);
    Assertions.assertEquals(incident.dateTime(), curentDateTime);
    Assertions.assertEquals(incident.observations(), observation);
    Assertions.assertEquals(incident.user(), user);

  }
}
