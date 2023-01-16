package controller.commands;

import model.GreyScaleEnum;
import model.PhotoModel;

/**
 * GreyScale class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the GreyScale function on an Image.
 */
public class GreyScale implements ImageProcessingCommand {
  String key;
  String newKey;
  GreyScaleEnum type;

  /**
   * instantiates the GreyScale class.
   *
   * @param key    represents the key of the image to be grey scaled.
   * @param newKey represents the key of the grey scaled image.
   * @param type   represents the type of greyScale.
   */
  public GreyScale(String key, String newKey, GreyScaleEnum type) {
    this.key = key;
    this.newKey = newKey;
    this.type = type;
  }

  /**
   * Executes the greyScale function on a photomodel.
   *
   * @param model represents a photo model.
   */
  @Override
  public void execute(PhotoModel model) {
    model.greyScale(key, newKey, type);

  }
}
