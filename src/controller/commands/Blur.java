package controller.commands;

import model.PhotoModel;

/**
 * Blur class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the Blur function on an Image.
 */
public class Blur implements ImageProcessingCommand {
  String key;
  String newKey;

  /**
   * Instantiates the Blur command class.
   *
   * @param key    represents the key to old image to be blurred.
   * @param newKey represents the key to the new blurred image.
   */
  public Blur(String key, String newKey) {
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
    model.blur(key, newKey);

  }
}
