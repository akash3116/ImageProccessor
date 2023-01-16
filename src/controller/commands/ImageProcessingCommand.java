package controller.commands;

import model.PhotoModel;

/**
 * Represents the ImageProcessCommand interface.
 * Contains all the different commands.
 * Execute function calls the given command on a photoModel.
 */
public interface ImageProcessingCommand {

  /**
   * Executes the given command on a photo model.
   *
   * @param model represents a photo model.
   */
  void execute(PhotoModel model);

}
