package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import model.Image;

/**
 * represents a gui view interface. contains all GUI view related functions.
 */
public interface GuiViewInterface {
  /**
   * Method to display the current image being edited on.
   * This takes an image from the controller and displays this image on the image label.
   * This method allows the user to see the current image that is being worked out.
   *
   * @param image represents the image to be displayed.
   */
  void displayImage(Image image);

  /**
   * Mehtod to display the Histogram image on the hist panel.
   *
   * @param image Buffered Image that is to be displayed.
   */
  void displayHistogram(BufferedImage image);

  /**
   * Method to display a pop-up in the view to ask the user to enter an increment.
   * this is used in features like brighten and darken where user input in required.
   *
   * @return returns the integer value of the user input.
   */
  int getIncrement();

  /**
   * Method to display a pop-up in the view to ask the user to enter an increment.
   * this is used in features like brighten and darken where user input in required.
   *
   * @return returns the integer value of the user input.
   */
  int getPercentage();

  /**
   * Method to display a pop-up error message if there is an invalid user input.
   *
   * @param error error message to be displayed.
   */
  void displayError(String error);

  /**
   * This method sets the action listener.
   * Allows for the controller to be the action listener.
   *
   * @param listener represents an action listener.
   */
  void setListener(ActionListener listener);
}
