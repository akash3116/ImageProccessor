package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Image;
import model.Pixel;

/**
 * View class for the GUI.
 * This class implements all GUI view related functions,
 * Contains all the buttons and panels required to display a functional GUI.
 * This class allocates a function to the controller when a client interacts with a button.
 * This extends the java swing JFrame class.
 */
public class GuiView extends JFrame implements GuiViewInterface {
  // add fields here (buttons etc.)
  private JPanel mainPanel;
  private JButton loadButton;
  private JButton saveButton;
  private JButton blur;
  private JButton sharpen;
  private JButton flipHorizontally;
  private JButton flipVertically;
  private JButton greyScale;
  private JButton greyscaleRed;
  private JButton greyscaleGreen;
  private JButton greyscaleBlue;
  private JButton greyscaleLuma;
  private JButton greyscaleIntensity;
  private JButton greyscaleValue;
  private JButton sepia;
  private JButton brighten;
  private JButton darken;
  private JButton downsize;
  private JLabel hist;
  private JLabel imageLabel;


  /**
   * Public constructor that initializes the GUIView class.
   * This constructor initializes all the panels and buttons used in this GUI.
   * This constructor also sets the layout for GUI.
   */
  public GuiView() {
    super();
    setTitle("Image processor");
    setSize(400, 400);
    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setLayout(new FlowLayout());
    this.add(mainPanel);
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(this.mainPanel);
    add(mainScrollPane);
    // dialog panel
    JPanel functions = new JPanel();
    functions.setLayout(new GridLayout(17, 1));
    functions.setBorder(BorderFactory.createTitledBorder("Functions"));
    functions.setLayout(new BoxLayout(functions, BoxLayout.PAGE_AXIS));
    this.mainPanel.add(functions);


    // load button
    loadButton = new JButton("Load");
    mainPanel.setLayout(new FlowLayout());
    loadButton.setActionCommand("load");
    //fileOpenDisplay = new JLabel("File path will appear here");
    functions.add(loadButton);

    // flip Vertically button
    flipVertically = new JButton("Flip Vertically");
    functions.add(flipVertically);
    flipVertically.setActionCommand("flipV");

    // flip Horizontally button
    flipHorizontally = new JButton("Flip Horizontally");
    functions.add(flipHorizontally);
    flipHorizontally.setActionCommand("flipH");


    // save button -
    saveButton = new JButton("Save");
    functions.add(saveButton);
    saveButton.setActionCommand("save");

    // blur button
    blur = new JButton("Blur");
    functions.add(blur);
    blur.setActionCommand("blur");

    // sharpen button
    sharpen = new JButton("Sharpen");
    functions.add(sharpen);
    sharpen.setActionCommand("sharpen");


    // greyScale button
    greyScale = new JButton("Greyscale");
    functions.add(greyScale);
    greyScale.setActionCommand("greyscale");

    //Brighten button
    brighten = new JButton("Brighten");
    functions.add(brighten);
    brighten.setActionCommand("brighten");

    //Darken button
    darken = new JButton("Darken");
    functions.add(darken);
    darken.setActionCommand("darken");

    //Greyscale red button
    greyscaleRed = new JButton("Greyscale Red");
    functions.add(greyscaleRed);
    greyscaleRed.setActionCommand("greyscaleRed");

    //Greyscale green button
    greyscaleGreen = new JButton("Greyscale Green");
    functions.add(greyscaleGreen);
    greyscaleGreen.setActionCommand("greyscaleGreen");

    //Greyscale blue button
    greyscaleBlue = new JButton("Greyscale Blue");
    functions.add(greyscaleBlue);
    greyscaleBlue.setActionCommand("greyscaleBlue");

    //Greyscale Luma button
    greyscaleLuma = new JButton("Greyscale Luma");
    functions.add(greyscaleLuma);
    greyscaleLuma.setActionCommand("greyscaleLuma");

    //Greyscale intensity button
    greyscaleIntensity = new JButton("Greyscale Intensity");
    functions.add(greyscaleIntensity);
    greyscaleIntensity.setActionCommand("greyscaleIntensity");

    //Greyscale Value button
    greyscaleValue = new JButton("Greyscale Value");
    functions.add(greyscaleValue);
    greyscaleValue.setActionCommand("greyscaleValue");

    //Downsize button
    downsize = new JButton("Downsize");
    functions.add(downsize);
    downsize.setActionCommand("downsize");


    // sepia Button
    sepia = new JButton("Sepia");
    functions.add(sepia);
    sepia.setActionCommand("sepia");

    //Jpanel for actual image
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Selected Image"));
    this.mainPanel.add(imagePanel);

    imageLabel = new JLabel();
    JScrollPane scroll = new JScrollPane(imageLabel);
    scroll.setPreferredSize(new Dimension(400, 400));
    imagePanel.add(scroll);

    //Jpanel for histogram
    JPanel histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("Histogram Display"));
    this.mainPanel.add(histogramPanel);

    // panel inside histogram
    hist = new JLabel();
    JScrollPane scroller = new JScrollPane(hist);
    scroller.setPreferredSize(new Dimension(400, 400));
    histogramPanel.add(scroller);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);


  }


  /**
   * Method to display the current image being edited on.
   * This takes an image from the controller and displays this image on the image label.
   * This method allows the user to see the current image that is being worked out.
   *
   * @param image represents the image to be displayed.
   */
  public void displayImage(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Pixel p = image.getPixel(j, i);
        Color color = new Color(checkBounds(p.getRed()), checkBounds(p.getGreen()),
                checkBounds(p.getBlue()));
        int pixel = color.getRGB();
        bi.setRGB(i, j, pixel);
      }
    }
    ImageIcon icon = new ImageIcon(bi);
    imageLabel.setIcon(icon);
  }

  /**
   * Mehtod to display the Histogram image on the hist panel.
   *
   * @param image Buffered Image that is to be displayed.
   */
  public void displayHistogram(BufferedImage image) {
    ImageIcon icon = new ImageIcon(image);
    hist.setIcon(icon);

  }


  /**
   * Method to display a pop-up in the view to ask the user to enter an increment.
   * this is used in features like brighten and darken where user input in required.
   *
   * @return returns the integer value of the user input.
   */
  public int getIncrement() {
    String value = JOptionPane.showInputDialog("Enter value");
    return Integer.parseInt(value);
  }

  /**
   * Method to display a pop-up in the view to ask the user to enter an increment.
   * this is used in features like brighten and darken where user input in required.
   *
   * @return returns the integer value of the user input.
   */
  public int getPercentage() {
    String value = JOptionPane.showInputDialog("Enter percentage");
    int val = 0;
    if (Integer.parseInt(value) < 0 || Integer.parseInt(value) > 100) {
      this.displayError("Invalid number for percentage downscale");
      return val;
    }
    return Integer.parseInt(value);
  }

  /**
   * Method to display a pop-up error message if there is an invalid user input.
   *
   * @param error error message to be displayed.
   */
  public void displayError(String error) {
    JOptionPane.showMessageDialog(this.mainPanel, error);
  }


  /**
   * This method sets the action listener.
   * Allows for the controller to be the action listener.
   *
   * @param listener represents an action listener.
   */
  public void setListener(ActionListener listener) {
    loadButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    flipVertically.addActionListener(listener);
    flipHorizontally.addActionListener(listener);
    blur.addActionListener(listener);
    sharpen.addActionListener(listener);
    greyscaleValue.addActionListener(listener);
    greyscaleIntensity.addActionListener(listener);
    greyscaleLuma.addActionListener(listener);
    greyscaleRed.addActionListener(listener);
    greyscaleGreen.addActionListener(listener);
    greyscaleBlue.addActionListener(listener);
    greyScale.addActionListener(listener);
    sepia.addActionListener(listener);
    darken.addActionListener(listener);
    brighten.addActionListener(listener);
    downsize.addActionListener(listener);
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


