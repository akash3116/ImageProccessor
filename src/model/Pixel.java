package model;

/**
 * represents the pixel class.
 * Contains all pixel related functions.
 * has three different color fields to represent the RGB values of a pixel.
 */
public class Pixel implements PixelImplInterface {
  private int red;
  private int green;
  private int blue;

  /**
   * Instantiates the pixel class.
   *
   * @param red   Red component of the pixel.
   * @param green green component of the pixel.
   * @param blue  blue component of the pixel.
   */
  public Pixel(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * method to get one of the colors of a pixel.
   *
   * @param color Color to be found.
   * @return Color in that pixel.
   */
  @Override
  public int getColor(String color) {
    int returnColor = 0;
    if (color.equalsIgnoreCase("red")) {
      returnColor = this.red;
    } else if (color.equalsIgnoreCase("green")) {
      returnColor = this.green;
    } else if (color.equalsIgnoreCase("blue")) {
      returnColor = this.blue;
    }
    return returnColor;
  }

  /**
   * gets the max component of the RGB values.
   *
   * @return max of the three RGB values.
   */
  @Override
  public int getMaxComponent() {
    if (this.red >= this.green && this.red >= this.blue) {
      return this.red;
    } else if (this.green >= this.red && this.green >= this.blue) {
      return this.green;
    } else {
      return this.blue;
    }
  }

  /**
   * computes the Avg value of the RGB values.
   *
   * @return avg of the RGB values.
   */
  @Override
  public int getAvgValue() {
    int avg = (this.red + this.green + this.blue) / 3;
    return avg;
  }

  /**
   * computes the Luma value of the RGB values.
   *
   * @return Luma of the RGB values.
   */
  @Override
  public int getLumaValue() {
    int luma = (int) ((0.2126 * this.red) + (0.7152 * this.green) + (0.0722 * this.blue));
    return luma;
  }

  /**
   * computes the red of the RGB values.
   *
   * @return red of the RGB values.
   */
  @Override
  public int getRed() {
    int red = this.red;
    return red;
  }

  /**
   * computes the green of the RGB values.
   *
   * @return green of the RGB values.
   */
  @Override
  public int getGreen() {
    int green = this.green;
    return green;
  }

  /**
   * computes the blue of the RGB values.
   *
   * @return blue of the RGB values.
   */
  @Override
  public int getBlue() {
    int blue = this.blue;
    return blue;
  }

}
