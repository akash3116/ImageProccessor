package view;

import java.io.IOException;

/**
 * represents the View class of the Photo.
 * This class outputs an Appendable.
 */
public class ImageView implements ViewInterface {
  private Appendable out;

  /**
   * instantiates the View class.
   *
   * @param out represents the Appendable.
   */
  public ImageView(Appendable out) {
    this.out = out;
  }

  /**
   * the renderMessage method transmits a message to the view.
   *
   * @param message the message to be transmitted.
   * @throws IOException if the message is improperly transmitted.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    out.append(message);

  }
}
