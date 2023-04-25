package ar.edu.utn.frba.dds.passwordvalidator;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TopCommonPasswordsFileReader {

  public boolean findPassword(String password) throws IOException {

    File file = new File("src\\main\\resources\\top10000.txt");

    Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
    String line = scanner.nextLine();

    while (scanner.hasNextLine()) {
      if (line.equals(password)) {
        return false;
      }
      line = scanner.nextLine();
    }

    return true;
  }
}
