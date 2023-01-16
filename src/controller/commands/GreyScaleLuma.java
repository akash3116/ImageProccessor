package controller.commands;

import model.PhotoModel;

/**
 * GreyScaleLuma class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the greyScaleLuma function on an Image.
 */
public class GreyScaleLuma implements ImageProcessingCommand {
  String key;
  String newKey;

  /**
   * Instantiates the GreyScaleLuma command class.
   *
   * @param key    represents the key to old image to be greyScaled.
   * @param newKey represents the key to the new greyScaled image.
   */
  public GreyScaleLuma(String key, String newKey) {
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
    model.greyscaleLuma(key, newKey);

  }
}
