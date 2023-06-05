package ar.edu.utn.frba.dds.passwordvalidator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TopCommonPasswordsFileReader {

  URL res = getClass().getClassLoader().getResource("top10000.txt");
  File file = Paths.get(res.toURI()).toFile();
  String absolutePath = file.getAbsolutePath();

  private List<String> sortedElements;
  private PasswordCache passwordCache;

  public TopCommonPasswordsFileReader(int limitForCache) throws URISyntaxException {
    this.sortedElements = new ArrayList<>();
    this.passwordCache = new PasswordCache(limitForCache);
  }

  public void sortPasswords() {
    BufferedReader reader = null;
    BufferedWriter writer = null;

    try {
      reader = new BufferedReader(
          new InputStreamReader(new FileInputStream(absolutePath), StandardCharsets.UTF_8));

      String currentLine = reader.readLine();

      while (currentLine != null) {
        sortedElements.add(currentLine);

        currentLine = reader.readLine();
      }

      writer = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream(absolutePath), StandardCharsets.UTF_8));

      for (String element : sortedElements) {
        writer.write(element);

        writer.newLine();
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }

        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void assertCacheAlreadyHas(String password) {
    if (passwordCache.alreadyHas(password)) {
      throw new InvalidPassword("Password is not strong enough.");
    }
  }

  public void assertIsInFileOrCache(String password) {

    int i = 0;

    assertCacheAlreadyHas(password);
    List<String> elementsWithLength =
        sortedElements.stream().filter(element -> element.length() == password.length()).toList();

    while (elementsWithLength.get(i) != null) {
      if (elementsWithLength.get(i).equals(password)) {
        passwordCache.addNewPassword(password);
        throw new InvalidPassword("Password is not strong enough.");
      }
      i++;
    }

  }
}
