package controller.commands;

import model.PhotoModel;

/**
 * Downsize command class.
 */
public class Downsize implements ImageProcessingCommand {
  String key;
  String newKey;
  int percentage;

  /**
   * Instantiates the Downsize class.
   *
   * @param key        key to old image.
   * @param newKey     key to new image.
   * @param percentage percentage to be downsized.
   */
  public Downsize(String key, String newKey, int percentage) {
    this.key = key;
    this.newKey = newKey;
    this.percentage = percentage;
  }

  /**
   * Executes the given command on a photo model.
   *
   * @param model represents a photo model.
   */
  @Override
  public void execute(PhotoModel model) {
    model.downsize(this.key, this.newKey, this.percentage);
  }
}
