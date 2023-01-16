package model;

/**
 * Interface that represents all photo editing functions.
 */
public interface PhotoEditor {

  /**
   * Method to flip an image horizontally.
   */
  public void flipHorizontally(String file, String newFile);

  /**
   * Method to flip an image vertically.
   */
  public void flipVertically(String file, String newFile);

  /**
   * method to Brighten an image.
   */
  public void brighten(String file, int increment, String newFile);

  /**
   * method to darken an image.
   */
  public void darken(String file, int increment, String newFile);


  /**
   * Method to greyScale an Model.Image.
   */
  public void greyScale(String name, String fileName, GreyScaleEnum types);

  /**
   * Method to get an image from the hashmap.
   * The method takes in a key which is a key to a specific image in the Hashmap ImageList.
   *
   * @param key represents the key to an image in the Hashmap ImageList.
   * @return return Image wih that key.
   */
  public Image getImage(String key);

  /**
   * Method to get an image from the hashMap.
   * Takes in a key that represents the key to the Inputted image in the Hashmap.
   *
   * @param key   represents the key in the hashmap to the image.
   * @param image represents the image being put into the hashmap.
   */
  public void putImage(String key, Image image);

  /**
   * Method to blur an Image with 'name' and put the blurred image into the imageList Hashmap.
   * With the key 'newName'.
   *
   * @param name    represents the key of the image to be blurred.
   * @param newName represents the key of the new blurred image.
   */
  public void blur(String name, String newName);

  /**
   * Method to sharpen an Image with 'name' and put the sharpened image into the imageList Hashmap.
   * With the key 'newName'.
   *
   * @param name    represents the key of the image to be sharpened.
   * @param newName represents the key of the new sharpened image.
   */
  public void sharpen(String name, String newName);

  /**
   * Method to greyScale Luma an image using the color transformation matrices. Takes in an image.
   * With key 'keyName' and places the new image with key 'newName' into the imageLst hashmap.
   *
   * @param keyName represents the key in the hashmap of the image to be greyscaled.
   * @param newName represents the key in the hashmap of the new greyscaled image.
   */
  public void greyscaleLuma(String keyName, String newName);

  /**
   * Method to sepia transform an image using the color transformation matrices. Takes in an image.
   * With key 'keyName' and places the new image with key 'newName' into the imageLst hashmap.
   *
   * @param keyName represents the key in the hashmap of the image to be sepia transform.
   * @param newName represents the key in the hashmap of the new sepia transformed image.
   */
  public void sepia(String keyName, String newName);

  /**
   * Method to downsize an Image by a percent specified by the client.
   * Takes in a key for the image to be downsized and places a new downsized image in the hashmap.
   * With key 'newKey'
   *
   * @param key     represents the key of the old image.
   * @param newKey  represents the key of the new image.
   * @param percent represents the percent to which the image should be downsized.
   * @throws IllegalArgumentException if the specified percent is invalid.
   */
  void downsize(String key, String newKey, int percent) throws IllegalArgumentException;

}
