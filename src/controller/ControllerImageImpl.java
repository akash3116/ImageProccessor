package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import javax.imageio.ImageIO;

import controller.commands.Blur;
import controller.commands.Darken;
import controller.commands.Downsize;
import controller.commands.GreyScaleLuma;
import controller.commands.Sepia;
import controller.commands.Sharpen;
import model.GreyScaleEnum;
import model.Image;
import model.PhotoModel;
import controller.commands.Brighten;
import controller.commands.FlipHorizontally;
import controller.commands.FlipVertically;
import controller.commands.GreyScale;
import controller.commands.ImageProcessingCommand;
import model.Pixel;
import view.ImageView;

/**
 * This class represents the implementation for the Image controller.
 * This class reads in user inputs and transmits to the model\view.
 */
public class ControllerImageImpl implements ControllerInterface {
  public Readable in;
  public PhotoModel model;
  public ImageView view;

  /**
   * Constructor instantiates the ControllerImageImpl.
   *
   * @param in    represents the readable.
   * @param model represents the PhotoModel.
   * @param view  represents the ImageView.
   */
  public ControllerImageImpl(Readable in, PhotoModel model, ImageView view) {
    this.in = in;
    this.model = model;
    this.view = view;
    initCommands();
  }

  /**
   * Constructor instantiates the ControllerImageImpl.
   *
   * @param model represents the PhotoModel.
   */
  public ControllerImageImpl(PhotoModel model) {
    this.model = model;
  }

  Map<String, Function<String[], ImageProcessingCommand>> commands;

  // command line Commands
  private void initCommands() {
    // TODO: put commands here.. ex)
    this.commands = new HashMap<>();
    commands.put("brighten", s -> new Brighten(s[1], Integer.parseInt(s[2]), s[3]));
    commands.put("darken", s -> new Darken(s[1], Integer.parseInt(s[2]), s[3]));
    commands.put("verticalFlip", s -> new FlipVertically(s[1], s[2]));
    commands.put("horizontalFlip", s -> new FlipHorizontally(s[1], s[2]));
    commands.put("redGreyscale", s -> new GreyScale(s[1], s[2], GreyScaleEnum.RED));
    commands.put("greenGreyscale", s -> new GreyScale(s[1], s[2], GreyScaleEnum.GREEN));
    commands.put("greyscale", s -> new GreyScaleLuma(s[1], s[2]));
    commands.put("sepia", s -> new Sepia(s[1], s[2]));
    commands.put("sharpen", s -> new Sharpen(s[1], s[2]));
    commands.put("blur", s -> new Blur(s[1], s[2]));
    commands.put("blueGreyscale", s -> new GreyScale(s[1], s[2], GreyScaleEnum.BLUE));
    commands.put("valueGreyscale", s -> new GreyScale(s[1], s[2], GreyScaleEnum.VALUE));
    commands.put("intensityGreyscale", s -> new GreyScale(s[1], s[2],
            GreyScaleEnum.INTENSITY));
    commands.put("lumaGreyscale", s -> new GreyScale(s[1], s[2], GreyScaleEnum.LUMA));
    commands.put("downsize", s -> new Downsize(s[1], s[2], Integer.parseInt(s[3])));
  }

  /**
   * Method that runs the program and handles user input.
   * Allocates to different commands depending on user input.
   *
   * @throws IOException if the command is null.
   */
  @Override
  public void runProgram() throws IOException {
    Scanner s = new Scanner(in);
    boolean quit = false;

    String inCommand = "";
    Function<String[], ImageProcessingCommand> commandFunction;

    while (s.hasNextLine()) {
      ImageProcessingCommand c;
      String[] in = s.nextLine().split(" ");
      for (String input : in) {
        if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
          quit = true;
          break;
        }
      }

      if (quit) {
        this.quitProgram();
        break;
      }

      try {
        inCommand = in[0];
        if (inCommand.equalsIgnoreCase("load")) {
          this.load(in[1], in[2]);

        } else if (inCommand.equals("save")) {
          this.saveImage(in[1], in[2]);

        } else {
          commandFunction = commands.getOrDefault(in[0], null);
          if (commandFunction == null) {
            this.errorMessage();
          } else {
            try {
              c = commandFunction.apply(in);
              c.execute(this.model);
            } catch (Exception e) {
              this.errorMessage();
            }
          }
        }
      } catch (IndexOutOfBoundsException e) {
        throw new IndexOutOfBoundsException();
      }
    }
  }

  // helper method to render an error message if an invalid command is passed in.
  private void errorMessage() throws IllegalStateException {
    try {
      this.view.renderMessage("try again, an invalid command was entered" + System.lineSeparator());
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  // helper function to quit the program and render a quit message.
  private void quitProgram() throws IOException {
    view.renderMessage("quit Program");
  }

  /**
   * Method to accept a file input and load the image.
   * Allows for images with types ppm/jpeg/jpg/bmp to be loaded.
   * Loads the image from the provided filepath into the imageList hashmap in the model.
   * This function places the image into the hashMap with key 'name'.
   *
   * @param name     represents the key of the image to be stored in the model's image list.
   * @param fileName represents the filepath of the file to be saved.
   * @throws IOException if invalid file type is provided.
   */
  @Override
  public void load(String name, String fileName) throws IOException {
    if (fileName.endsWith(".ppm")) {
      Image image = Image.readPPM(fileName);
      this.model.putImage(name, image);
    } else if ((fileName.endsWith("jpg")) || (fileName.endsWith(".jpeg")) ||
            (fileName.endsWith(".png")) || (fileName.endsWith(".bmp"))) {
      BufferedImage image = ImageIO.read(new File(fileName));
      int height = image.getHeight();
      int width = image.getWidth();
      Pixel[][] pixelList = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color color = new Color(image.getRGB(j, i));
          int red = color.getRed();
          int green = color.getGreen();
          int blue = color.getBlue();
          pixelList[i][j] = new Pixel(red, green, blue);
        }
      }
      Image newImage = new Image(width, height, pixelList, 255);
      this.model.putImage(name, newImage);

    } else {
      throw new IllegalArgumentException("invalid input file type");
    }

  }

  /**
   * Method to save any type of image.
   * Checks the file type ands uses a different save functionality accordingly.
   * Allows the client to save any image in the model imagelist hashap as a jpeg/jpg/ppm/bmp.
   *
   * @param newFilePath represents the new file path the client wishes to save the image to.
   * @param oldFileName represents the key to the image in the model imageList hashmap that is.
   *                    to be saved.
   */
  @Override
  public void saveImage(String newFilePath, String oldFileName) {
    if (newFilePath.endsWith("ppm")) {
      File newFile = new File(newFilePath);
      Image newPicture = this.model.getImage(oldFileName);
      Pixel[][] newPixelList = newPicture.getImage();
      StringBuilder newFileContents = new StringBuilder();
      newFileContents.append("P3" + " \n");
      newFileContents.append(newPicture.getWidth() + " ");
      newFileContents.append(newPicture.getHeight() + " \n");
      newFileContents.append(newPicture.getMax() + " \n");

      for (int i = 0; i < newPicture.getHeight(); i++) {
        for (int j = 0; j < newPicture.getWidth(); j++) {
          Pixel p1 = newPicture.getPixel(i, j);
          newFileContents.append(p1.getRed() + " ");
          newFileContents.append(p1.getGreen() + " ");
          newFileContents.append(p1.getBlue() + " ");
        }
      }

      String oldFileContents = newFileContents.toString();

      try {
        FileWriter writer = new FileWriter(newFile);
        // newFile.createNewFile();
        writer.write(oldFileContents);
        writer.close();
      } catch (IOException e) {
        throw new IllegalArgumentException();
      }
    } else if ((newFilePath.endsWith("jpg")) || (newFilePath.endsWith("jpeg")) ||
            (newFilePath.endsWith("png")) || (newFilePath.endsWith("bmp"))) {
      Image imageToBeSaved = this.model.getImage(oldFileName);
      int width = imageToBeSaved.getWidth();
      int height = imageToBeSaved.getHeight();
      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      for (int i = 0; i < width; i++) {
        for (int j = 0; j < height; j++) {
          Pixel p = imageToBeSaved.getPixel(j, i);
          Color color = new Color(checkBounds(p.getRed()), checkBounds(p.getGreen()),
                  checkBounds(p.getBlue()));
          int pixel = color.getRGB();
          bi.setRGB(i, j, pixel);
        }
      }

      try {
        File file = new File(newFilePath);
        ImageIO.write(bi, newFilePath.split("\\.")[1], file);

      } catch (IOException i) {
        throw new IllegalStateException(i.getMessage());
      }
    } else {
      throw new IllegalArgumentException("Invalid file type");
    }
  }

  // helper method to ensure that pixel colors of non ppm images stay below 255 and above 0.
  private int checkBounds(int num) {
    if (num > 255) {
      return 255;
    } else {
      return Math.max(num, 0);
    }

  }

}
