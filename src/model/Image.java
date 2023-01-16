package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class represents an Image. Contains all Image related functions.
 * An Image has fields for the width, height, max, and PixelList.
 */
public class Image implements ImageImplInterface {
  private int width;
  private int height;
  private int max;
  private Pixel[][] pixelList;

  /**
   * Instantiates the Image class.
   *
   * @param width  represents the width of an Image.
   * @param height represents the height of an Image.
   * @param image  represents the 2D ArrayList of pixels in an Image.
   * @param max    represents the max value a pixel color can have in an Image.
   */
  public Image(int width, int height, Pixel[][] image, int max) {
    this.width = width;
    this.height = height;
    this.pixelList = image;
    this.max = max;
  }

  /**
   * Empty constructor that instantiates the Image class.
   */
  public Image() {
    // empty constructor that instantiates the image class.
  }

  /**
   * Method to get the height of an image.
   *
   * @return Image height.
   */
  @Override
  public int getHeight() {
    return this.height;
  }

  /**
   * Method to get the width of an image.
   *
   * @return Image width.
   */
  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * Method to get the max of an image.
   *
   * @return Max pixel color value of an Image.
   */
  @Override
  public int getMax() {

    return this.max;
  }

  /**
   * Method to get 2D arrayList of pixels for an Image.
   *
   * @return 2D arrayList of pixels.
   */
  @Override
  public Pixel[][] getImage() {
    Pixel[][] newArray = new Pixel[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        newArray[i][j] = this.pixelList[i][j];
      }
    }
    return newArray;
  }

  /**
   * returns Pixel at given position of a pixelList.
   *
   * @param i column of pixel.
   * @param j row of pixel.
   * @return Pixel at i and j.
   */
  @Override
  public Pixel getPixel(int i, int j) {
    return this.pixelList[i][j];
  }

  /**
   * method to scan an Image and obtain its contents.
   *
   * @param filename path from which image is taken.
   * @return an Image generated from scanning the file.
   */
  public static Image readPPM(String filename) {
    Image image = new Image();
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalStateException("File not Found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    image.width = width;

    int height = sc.nextInt();
    image.height = height;
    int maxValue = sc.nextInt();
    image.max = maxValue;

    Pixel[][] picture = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        picture[i][j] = new Pixel(r, g, b);
      }
      image.pixelList = picture;
    }
    return image;
  }

  /**
   * Returns list of frequencies for a specified color.
   * @param color What RGB color is wanted.
   * @return integer list of frequencies.
   */
  @Override
  public int[] redFrequency(String color) {
    int[] redList = new int[256];
    int[] greenList = new int[256];
    int[] blueList = new int[256];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel p = getPixel(i, j);
        int redVal = p.getRed();
        redList[redVal] += 1;
        int blueVal = p.getBlue();
        blueList[blueVal] += 1;
        int greenVal = p.getGreen();
        greenList[greenVal] += 1;
      }
    }
    if (color.equalsIgnoreCase("red")) {
      return redList;
    } else if (color.equalsIgnoreCase("green")) {
      return greenList;
    } else {
      return blueList;
    }
  }

  /**
   * Method to return the list of frequency of pixels with intensity.
   * @return frequency of pixels in list.
   */
  @Override
  public int[] intensity() {
    int[] list = new int[256];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel p = getPixel(i, j);
        int val = (p.getRed() + p.getBlue() + p.getGreen()) / 3;
        list[val] += 1;
      }
    }
    return list;
  }

  // Helper method to ensure the pixel bounds are correct.
  private int checkBounds(int num) {
    int max = this.getMax();

    if (num > max) {
      return max;
    } else if (num < 0) {
      return 0;
    } else {
      return num;
    }
  }
}