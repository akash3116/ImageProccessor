package controller.commands;

import model.PhotoModel;

/**
 * Sharpen class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the Sharpen function on an Image.
 */
public class Sharpen implements ImageProcessingCommand {
  String key;
  String newKey;

  /**
   * Instantiates the Sharpen command class.
   *
   * @param key    represents the key to old image to be sharpened.
   * @param newKey represents the key to the new sharpened image.
   */
  public Sharpen(String key, String newKey) {
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
    model.sharpen(key, newKey);

  }
}
