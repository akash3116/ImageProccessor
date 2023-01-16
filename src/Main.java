import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.ControllerImageImpl;
import controller.SwingController;
import model.PhotoModel;
import view.ImageView;

/**
 * The main class which allows the client to run the program, providing instructions via the
 * console. See the README to understand how to interact with the console.
 */
public class Main {
  /**
   * The main method which allows the client to run the program, providing instructions via the
   * console.
   * the user will have  to interact.
   * * with the console by passing in commands that can be handled by the controller.
   *
   * @param args String arguments.
   * @throws IOException for invalid inputs.
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException,
          InstantiationException, IllegalAccessException {
    ImageView view = new ImageView(System.out);
    PhotoModel model = new PhotoModel(new HashMap<>());
    Readable in;

    if (args.length > 0) {
      if (args.length == 2 && args[0].equals("-file")) {
        String filepath = args[1];
        Scanner s = new Scanner(new FileInputStream(filepath));
        FileReader fileReader = null;
        if (filepath.endsWith(".txt")) {
          File file = new File(filepath);
          try {
            fileReader = new FileReader(file);
          } catch (IOException e) {
            throw new IllegalArgumentException("Bruh.");
          }
        }
        in = fileReader;

      } else {
        in = new InputStreamReader(System.in);
      }
      ControllerImageImpl c = new ControllerImageImpl(in, model, view);
      c.runProgram();
    } else {
      SwingController s = new SwingController();



      try {
        // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      } catch (UnsupportedLookAndFeelException e) {
        throw new UnsupportedEncodingException();
        // handle exception
      } catch (ClassNotFoundException e) {
        throw new ClassNotFoundException();
        // handle exception
      } catch (InstantiationException e) {
        throw new InstantiationException();
        // handle exception
      } catch (IllegalAccessException e) {
        throw new IllegalAccessException();
        // handle exception
      } catch (Exception e) {
        throw new IllegalArgumentException();
      }

    }
  }
}


