package controller.commands;

import model.PhotoModel;

/**
 * FlipVertically class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the vertical flip function on an Image.
 */
public class FlipVertically implements ImageProcessingCommand {
  String file;
  String newFile;

  /**
   * Instantiates the FlipVertically class.
   *
   * @param file    represents the key of the image to be flipped.
   * @param newFile represents the key of the flipped image.
   */
  public FlipVertically(String file, String newFile) {
    this.file = file;
    this.newFile = newFile;
  }

  /**
   * Executes the flip function on a photomodel.
   *
   * @param model represents a photo model.
   */
  @Override
  public void execute(PhotoModel model) {
    model.flipVertically(this.file, this.newFile);
  }
}
