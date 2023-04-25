package ar.edu.utn.frba.dds.passwordvalidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class TopCommonPasswordsFileReader {

  public boolean findPassword(String password) throws IOException {

    File file = new File("top10000.txt");
    if (!file.exists()) {
      throw new FileNotFoundException();
    } else {
      String filePath = file.getAbsolutePath();

      Scanner scanner = new Scanner(filePath);
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
}
