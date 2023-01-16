package controller;

import java.io.IOException;

/**
 * represents the interface for the photo controller.
 */
public interface ControllerInterface {

  /**
   * Function to run the program.
   */
  void runProgram() throws IOException;

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
  public void load(String name, String fileName) throws IOException;

  /**
   * Method to save any type of image.
   * Checks the file type ands uses a different save functionality accordingly.
   * Allows the client to save any image in the model imagelist hashap as a jpeg/jpg/ppm/bmp.
   *
   * @param newFilePath represents the new file path the client wishes to save the image to.
   * @param oldFileName represents the key to the image in the model imageList hashmap that is.
   *                    to be saved.
   */
  public void saveImage(String newFilePath, String oldFileName);

}

