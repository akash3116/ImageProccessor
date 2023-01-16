package view;

import java.io.IOException;

/**
 * interface represents the view. Contains all view related methods.
 */
public interface ViewInterface {

  /**
   * renders message to the appendable.
   *
   * @param message message to be rendered.
   * @throws IOException if null.
   */
  void renderMessage(String message) throws IOException;
}
