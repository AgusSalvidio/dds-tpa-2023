package ar.edu.utn.frba.dds.passwordvalidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TopCommonPasswordsFileReader {
  private static final String TOP10000 = "./top10000";

  public void findPassword(String password) throws IOException {
    String filePath = Objects.requireNonNull(getClass().getResource(TOP10000)).getFile();
    BufferedReader buffer = null;

    try {
      buffer = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));
      String line = buffer.readLine();

      while (line != null) {
        if (line.equals(password)) {
          throw new RuntimeException("The password is not strong enough.");
        }
        line = buffer.readLine();
      }
    } catch (IOException error) {
      throw new RuntimeException(
          "An error occurred while reading the file. Message: " + error.getMessage());
    } finally {
      assert buffer != null;
      buffer.close();
    }
  }
}
