package controller.commands;

import model.PhotoModel;

/**
 * Brighten class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the brighten function on an Image.
 */
public class Brighten implements ImageProcessingCommand {

  String key;
  int increment;
  String newKey;

  /**
   * Instantiates the Brighten class.
   *
   * @param key       represents the key in the String -> Image hashmap in model.
   * @param increment represnts thge int value of increment.
   * @param newKey    represnts the new key the new Image should be stored as in the hashmap.
   */
  public Brighten(String key, int increment, String newKey) {
    this.key = key;
    this.increment = increment;
    this.newKey = newKey;
  }

  /**
   * excutes brighten on a photo model.
   *
   * @param model represents a photo model.
   */
  @Override
  public void execute(PhotoModel model) {
    model.brighten(this.key, this.increment, this.newKey);
  }
}
