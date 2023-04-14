package ar.edu.utn.frba.dds.passwordValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TopCommonPasswordsFileReader {
  private static final String TOP10000 = "./top10000";

  public void findPassword(String password) throws IOException {
    String filePath = getClass().getResource(TOP10000).getFile();
    BufferedReader buffer = null;

    try {
      buffer = new BufferedReader(new FileReader(filePath));
      String line = buffer.readLine();

      while (line != null) {
        if (line == password) {
          throw new RuntimeException("The password is not strong enough.");
        }
        line = buffer.readLine();
      }
    } catch (IOException error) {
      throw new RuntimeException("An error occurred while reading the file. Message: " + error.getMessage());
    } finally {
      buffer.close();
    }
  }
}
