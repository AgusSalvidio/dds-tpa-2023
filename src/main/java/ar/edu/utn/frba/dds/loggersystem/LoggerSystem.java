package ar.edu.utn.frba.dds.loggersystem;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerSystem {

  String filePath = "./logs.log";
  Logger logger;
  Handler fileHandler;

  public LoggerSystem() throws Exception {
    this.configureLogger();
  }

  private void configureLogger() throws Exception {
    this.logger = Logger.getLogger("Logger");
    this.fileHandler = new FileHandler(filePath, false);

    SimpleFormatter simpleFormatter = new SimpleFormatter();
    fileHandler.setFormatter(simpleFormatter);

    this.logger.addHandler(fileHandler);

    this.log(Level.INFO, "Proceso de logueo inicializado");

  }

  public void log(Level level, String message) {
    this.logger.log(level, message);
  }

}
