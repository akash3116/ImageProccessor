package model;

/**
 * represents the Pixel interface. Contains all pixel related functions.
 */
public interface PixelImplInterface {

  /**
   * method to get one of the colors of a pixel.
   *
   * @param color Color to be found.
   * @return Color in that pixel.
   */
  int getColor(String color);

  /**
   * gets the max component of the RGB values.
   *
   * @return max of the three RGB values.
   */
  int getMaxComponent();

  /**
   * computes the Avg value of the RGB values.
   *
   * @return avg of the RGB values.
   */
  int getAvgValue();

  /**
   * computes the Luma value of the RGB values.
   *
   * @return Luma of the RGB values.
   */
  int getLumaValue();

  /**
   * computes the red of the RGB values.
   *
   * @return red of the RGB values.
   */
  int getRed();

  /**
   * computes the green of the RGB values.
   *
   * @return green of the RGB values.
   */
  int getGreen();

  /**
   * computes the blue of the RGB values.
   *
   * @return blue of the RGB values.
   */
  int getBlue();
}
