package model;

/**
 * represents the interface for an Image. Contains all image related functions.
 */
public interface ImageImplInterface {

  /**
   * Method to get the height of an image.
   *
   * @return Image height.
   */
  int getHeight();

  /**
   * Method to get the width of an image.
   *
   * @return Image width.
   */
  int getWidth();

  /**
   * Method to get the max of an image.
   *
   * @return Max pixel color value of an Image.
   */
  int getMax();

  /**
   * Method to get 2D arrayList of pixels for an Image.
   *
   * @return 2D arrayList of pixels.
   */
  Pixel[][] getImage();

  /**
   * returns Pixel at given position of a pixelList.
   *
   * @param i column of pixel.
   * @param j row of pixel.
   * @return Pixel at i and j.
   */
  Pixel getPixel(int i, int j);


  /**
   * Returns list of frequencies for a specified color.
   * @param color What RGB color is wanted.
   * @return integer list of frequencies.
   */
  int[] redFrequency(String color);

  /**
   * Method to return the list of frequency of pixels with intensity.
   * @return frequency of pixels in list.
   */
  int[] intensity();
}
