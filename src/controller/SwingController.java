package controller;


import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import model.GreyScaleEnum;
import model.Image;
import model.PhotoEditor;
import model.PhotoModel;
import model.Pixel;
import view.GuiView;
import view.GuiViewInterface;

/**
 * The SwingController class.
 * This class handles all user interaction for the GUI.
 * This class passes user actions to both the model and the view to display the required.
 * Content on the GUI.
 * This class implements the ActionListener interface to interact with user input on the GUI.
 */
public class SwingController implements ActionListener {
  private PhotoEditor model;
  private GuiViewInterface view;
  private String currentKey;

  /**
   * Instantiates the SwingController class.
   */
  public SwingController() {
    this.model = new PhotoModel(new HashMap<>());
    this.view = new GuiView();
    this.view.setListener(this);
  }

  /**
   * Instantiates the swing controller class.
   * @param model represents the model.
   * @param view represents the view.
   */
  public SwingController(PhotoEditor model, GuiViewInterface view) {
    this.model = model;
    this.view = view;
    this.view.setListener(this);
  }


  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "load": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showOpenDialog((Component) view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String imageFilePath = f.getAbsolutePath();
          if (imageFilePath.endsWith(".ppm")) {
            Image image = Image.readPPM(imageFilePath);
            this.model.putImage("load", image);
            this.currentKey = "load";
            this.view.displayImage(image);
            this.displayHist(image);


          } else if ((imageFilePath.endsWith("jpg")) || (imageFilePath.endsWith("jpeg"))
                  ||
                  (imageFilePath.endsWith("png")) || (imageFilePath.endsWith("bmp"))) {
            BufferedImage image = null;
            try {
              image = ImageIO.read(new File(imageFilePath));
            } catch (IOException ex) {
              ex.printStackTrace();
            }
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
            this.model.putImage("load", newImage);
            this.currentKey = "load";
            this.view.displayImage(newImage);
            this.displayHist(newImage);
          } else {
            this.view.displayError("Invalid Image file type");
          }
        }

        break;

      }
      case "save": {
        final JFileChooser fchooser = new JFileChooser(".");
        ControllerImageImpl c = new ControllerImageImpl((PhotoModel) this.model);
        int retvalue = fchooser.showSaveDialog((Component) view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String saveFilePath = f.getAbsolutePath();

          c.saveImage(saveFilePath, this.currentKey);
        }
        break;
      }
      case "flipV": {
        this.model.flipVertically(this.currentKey, "flippedH");
        Image editedImage = this.model.getImage("flippedH");
        this.currentKey = "flippedH";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "flipH": {
        this.model.flipHorizontally(this.currentKey, "flippedV");
        Image editedImage = this.model.getImage("flippedV");
        this.currentKey = "flippedV";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "blur": {
        this.model.blur(this.currentKey, "blur");
        Image editedImage = this.model.getImage("blur");
        this.currentKey = "blur";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "sharpen": {
        this.model.sharpen(this.currentKey, "sharpen");
        Image editedImage = this.model.getImage("sharpen");
        this.currentKey = "sharpen";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "sepia": {
        this.model.sepia(this.currentKey, "sepia");
        Image editedImage = this.model.getImage("sepia");
        this.currentKey = "sepia";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscale": {
        this.model.greyscaleLuma(this.currentKey, "greyscale");
        Image editedImage = this.model.getImage("greyscale");
        this.currentKey = "greyscale";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscaleRed": {
        this.model.greyScale(this.currentKey, "red", GreyScaleEnum.RED);
        Image editedImage = this.model.getImage("red");
        this.currentKey = "red";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscaleGreen": {
        this.model.greyScale(this.currentKey, "green", GreyScaleEnum.GREEN);
        Image editedImage = this.model.getImage("green");
        this.currentKey = "green";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscaleBlue": {
        this.model.greyScale(this.currentKey, "blue", GreyScaleEnum.BLUE);
        Image editedImage = this.model.getImage("blue");
        this.currentKey = "blue";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscaleLuma": {
        this.model.greyScale(this.currentKey, "luma", GreyScaleEnum.LUMA);
        Image editedImage = this.model.getImage("luma");
        this.currentKey = "luma";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscaleValue": {
        this.model.greyScale(this.currentKey, "value", GreyScaleEnum.VALUE);
        Image editedImage = this.model.getImage("value");
        this.currentKey = "value";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      case "greyscaleIntensity": {
        this.model.greyScale(this.currentKey, "intensity", GreyScaleEnum.INTENSITY);
        Image editedImage = this.model.getImage("intensity");
        this.currentKey = "intensity";
        this.view.displayImage(editedImage);
        break;
      }

      case "darken": {
        int increment = this.view.getIncrement();
        this.model.darken(this.currentKey, increment, "darken");
        Image editedImage = this.model.getImage("darken");
        this.currentKey = "darken";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);

        break;
      }

      case "brighten": {
        int increment = this.view.getIncrement();
        this.model.brighten(this.currentKey, increment, "brighten");
        Image editedImage = this.model.getImage("brighten");
        this.currentKey = "brighten";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }

      case "downsize": {
        int percentage = this.view.getPercentage();
        this.model.downsize(this.currentKey, "downsize", percentage);
        Image editedImage = this.model.getImage("downsize");
        this.currentKey = "downsize";
        this.view.displayImage(editedImage);
        this.displayHist(editedImage);
        break;
      }
      default:
        System.out.println("no valid case");
        break;

    }
  }

  // Helper method to create the Image for a histogram. Returns a buffered Image of the histogram.
  private BufferedImage makeGraph(int[] red, int[] blue, int[] green, int[] intensity) {
    int max = this.getFrequencyMax(red, blue, green, intensity);
    int scale = (int) (max / 400);
    BufferedImage bi = new BufferedImage(255, 400, BufferedImage.TYPE_INT_RGB);
    Graphics2D graph = bi.createGraphics();
    drawLines(new Color(1f, 0f, 0f, .5f), red, graph, scale);
    drawLines(new Color(0f, 1f, 0f, .5f), green, graph, scale);
    drawLines(new Color(0f, 0f, 1f, .5f), blue, graph, scale);
    drawLines(new Color(0.25f, 0.5f, 0.25f, .5f), intensity, graph, scale);
    return bi;

  }

  // Helper method to draw the lines for a histogram graph.
  private void drawLines(Color graphColor, int[] rgb, Graphics2D graph, int scale) {
    graph.setColor(graphColor);
    for (int i = 0; i < 256; i++) {
      graph.drawLine(i, 400, i,
              Math.min(400, 400 - rgb[i] / scale));
    }
  }

  // helper method to get the max of an array List of integers.
  private int getMax(int[] values) {
    int max = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] > max) {
        max = values[i];
      }
    }
    return max;
  }

  // helper to get the max int from three different array lists of integers.
  private int getFrequencyMax(int[] values, int[] values2, int[] values3, int[] values4) {
    return Math.max(Math.max(this.getMax(values), this.getMax(values4)),
            Math.max(this.getMax(values2), this.getMax(values3)));
  }

  /**
   * helper to display image.
   * @param editedImage represnts the image to be displayed.
   */
  public void displayHist(Image editedImage) {
    if (editedImage == null) {
      return;
    }
    BufferedImage hist = makeGraph(editedImage.redFrequency("red"),
            editedImage.redFrequency("blue"), editedImage.redFrequency("green"),
            editedImage.intensity());
    this.view.displayHistogram(hist);
  }
}
