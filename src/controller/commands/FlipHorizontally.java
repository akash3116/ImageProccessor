package controller.commands;

import model.PhotoModel;

/**
 * FlipHorizontally class (Command Design) that implements the ImageProcessingCommand interface.
 * The command class used to execute the flip Horizontally function on an Image.
 */
public class FlipHorizontally implements ImageProcessingCommand {
  String file;
  String newFile;

  /**
   * Instantiates the FlipHorizontally class.
   *
   * @param file    represents the key of the image to be flipped.
   * @param newFile represents the key of the new flipped image.
   */
  public FlipHorizontally(String file, String newFile) {
    this.file = file;
    this.newFile = newFile;
  }

  /**
   * executes flip on a photomodel.
   *
   * @param model represents a photo model.
   */
  @Override
  public void execute(PhotoModel model) {
    model.flipHorizontally(this.file, this.newFile);

  }
}
