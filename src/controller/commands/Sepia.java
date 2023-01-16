package controller.commands;

import model.PhotoModel;

/**
 * Sepia class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the sepia transform function on an Image.
 */
public class Sepia implements ImageProcessingCommand {
  String key;
  String newKey;

  /**
   * Instantiates the GreyScaleLuma command class.
   *
   * @param key    represents the key to old image to be sepia transformed.
   * @param newKey represents the key to the new sepia transform image.
   */
  public Sepia(String key, String newKey) {
    this.key = key;
    this.newKey = newKey;
  }

  /**
   * Executes the given command on a photo model.
   *
   * @param model represents a photo model.
   */
  @Override
  public void execute(PhotoModel model) {
    model.sepia(key, newKey);

  }
}
