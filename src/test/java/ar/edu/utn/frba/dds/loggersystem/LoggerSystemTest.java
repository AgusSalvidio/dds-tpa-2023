package ar.edu.utn.frba.dds.loggersystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LoggerSystemTest {

  @Test
  @DisplayName("Create logger")
  public void createLoggerTest() throws Exception {
    LoggerSystem loggerSystem = new LoggerSystem();

    String infoMessage = "Mensaje de Info";
    String warningMessage = "Mensaje de Advertencia";

    loggerSystem.log(Level.INFO, infoMessage);
    loggerSystem.log(Level.WARNING, warningMessage);

    FileReader fileReader = null;
    try {
      fileReader = new FileReader("logs.log");
    } catch (
        FileNotFoundException fe) {
      System.out.println("File not found");
    }

    BufferedReader br = new BufferedReader(fileReader);

    //The even lines are the messages, the odd ones are the timestamp -asalvidio

    List<String> lines = br.lines().collect(Collectors.toList());

    String secondLine = lines.get(3);
    String fourthLine = lines.get(5);

    Assertions.assertEquals(secondLine, "INFO: ".concat(infoMessage));
    Assertions.assertEquals(fourthLine, "WARNING: ".concat(warningMessage));

  }

}
