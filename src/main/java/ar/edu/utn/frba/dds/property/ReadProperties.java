package ar.edu.utn.frba.dds.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
  private static ReadProperties instance = null;
  private Properties prop;

  public static ReadProperties getInstance() {
    if (instance == null) {
      instance = new ReadProperties();
    }
    return instance;
  }

  private ReadProperties() {
    try {
      InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
      this.prop = new Properties();
      this.prop.load(input);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getJakartaUsername() {
    return this.prop.getProperty("jakarta.username");
  }

  public String getJakartaPassword() {
    return this.prop.getProperty("jakarta.password");
  }

  public String getTwilioAccountSid() {
    return this.prop.getProperty("twilio.accountSid");
  }

  public String getTwilioAuthToken() {
    return this.prop.getProperty("twilio.authToken");
  }

  public String getTwilioPhoneNumber() {
    return this.prop.getProperty("twilio.phoneNumber");
  }
}