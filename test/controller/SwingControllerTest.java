package controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import model.GreyScaleEnum;
import model.Image;
import model.PhotoEditor;
import view.GuiViewInterface;

import static org.junit.Assert.assertEquals;

/**
 * represents the class used in testing the swingController.
 */
public class SwingControllerTest {
  SwingController controller;
  PhotoEditor model;
  StringBuilder modelLog;
  GuiViewInterface view;
  StringBuilder viewLog;

  @Before
  public void init() {
    this.modelLog = new StringBuilder();
    this.model = new MockImageProcessingModelImpl(this.modelLog);

    this.viewLog = new StringBuilder();
    this.view = new MockImageProcessingSwingView(this.viewLog);

    this.controller = new SwingController(this.model, this.view);
  }

  // Test the flip horizontally Button.
  @Test
  public void actionPerformedHorizontalFlip() {
    ActionEvent e = new ActionEvent(this.view, 0, "flipH");
    this.controller.actionPerformed(e);

    assertEquals(this.modelLog.toString(), "flip");
  }

  // Test the flip vertically Button.

  @Test

  public void actionPerformedVerticallyFlip() {
    ActionEvent e = new ActionEvent(this.view, 0, "flipV");
    this.controller.actionPerformed(e);

    assertEquals(this.modelLog.toString(), "flip nullvertically and save it as 'flippedH'\n");
  }

  // Test the Sepia Button.

  @Test

  public void actionPerformedSepia() {
    ActionEvent e = new ActionEvent(this.view, 0, "sepia");
    this.controller.actionPerformed(e);

    assertEquals(this.modelLog.toString(), "sepia nulland save it as 'sepia'\n");
  }

  // test the brighten button

  @Test

  public void actionPerformedBrighten() {
    ActionEvent e = new ActionEvent(this.view, 0, "brighten");
    this.controller.actionPerformed(e);

    assertEquals(this.modelLog.toString(), "Brighten nulland save it as 'brighten'\n");
  }

  // test the darken button

  @Test

  public void actionPerformedDarken() {
    ActionEvent e = new ActionEvent(this.view, 0, "darken");
    this.controller.actionPerformed(e);

    assertEquals(this.modelLog.toString(), "darken nulland save it as 'darken'\n");
  }

  // test the downsize button

  @Test

  public void actionPerformedDownsize() {
    ActionEvent e = new ActionEvent(this.view, 0, "downsize");
    this.controller.actionPerformed(e);

    assertEquals(this.modelLog.toString(), "downsize button works");
  }

  // test the greyscale button

  @Test

  public void actionPerformedGreyscale() {
    ActionEvent e = new ActionEvent(this.view, 0, "greyscale");
    this.controller.actionPerformed(e);
    assertEquals(this.modelLog.toString(), "greyScaleLuma nulland save it as 'greyscale'\n");
  }






  private static class MockImageProcessingModelImpl implements PhotoEditor {
    private final StringBuilder log;

    public MockImageProcessingModelImpl(StringBuilder log) {
      this.log = log;
    }


    /**
     * Method to flip an image horizontally.
     *
     * @param file file to be saved.
     * @param newFile newfile to be saved.
     */
    @Override
    public void flipHorizontally(String file, String newFile) {
      this.log.append("flip");
    }

    /**
     * Method to flip an image vertically.
     *
     * @param file file.
     * @param newFile newfile.
     */
    @Override
    public void flipVertically(String file, String newFile) {
      this.log.append("flip ")
              .append(file)
              .append("vertically and save it as '")
              .append(newFile)
              .append("'\n");
    }


    /**
     * method to Brighten an image.
     *
     * @param file file to be saved.
     * @param increment increment.
     * @param newFile new file to be saved.
     */
    @Override
    public void brighten(String file, int increment, String newFile) {
      this.log.append("Brighten ")
              .append(file)
              .append("and save it as '")
              .append(newFile)
              .append("'\n");
    }

    /**
     * method to darken an image.
     *
     * @param file file to be saved.
     * @param increment increment.
     * @param newFile new file to be saved.
     */
    @Override
    public void darken(String file, int increment, String newFile) {
      this.log.append("darken ")
              .append(file)
              .append("and save it as '")
              .append(newFile)
              .append("'\n");
    }


    /**
     * Method to greyScale an Model Image.
     *
     * @param name name to be saved.
     * @param fileName filename to be saved.
     * @param types types of greyscale.
     */
    @Override
    public void greyScale(String name, String fileName, GreyScaleEnum types) {
      this.log.append("greyScale ")
              .append(name)
              .append("and save it as '")
              .append(fileName)
              .append("'\n");
    }

    /**
     * Method to get an image from the hashmap.
     * The method takes in a key which is a key to a specific image in the Hashmap ImageList.
     *
     * @param key represents the key to an image in the Hashmap ImageList.
     * @return return Image wih that key.
     */
    @Override
    public Image getImage(String key) {
      return null;
    }

    /**
     * Method to get an image from the hashMap.
     * Takes in a key that represents the key to the Inputted image in the Hashmap.
     *
     * @param key   represents the key in the hashmap to the image.
     * @param image represents the image being put into the hashmap.
     */
    @Override
    public void putImage(String key, Image image) {
      this.log.append("save ");
    }


    /**
     * Method to blur an Image with 'name' and put the blurred image into the imageList Hashmap.
     * With the key 'newName'.
     *
     * @param name    represents the key of the image to be blurred.
     * @param newName represents the key of the new blurred image.
     */
    @Override
    public void blur(String name, String newName) {
      this.log.append("blur ")
              .append(name)
              .append("and save it as '")
              .append(newName)
              .append("'\n");
    }


    /**
     * Method to sharpen an Image with 'name' and put the sharpened.
     * image into the imageList Hashmap.
     * With the key 'newName'.
     *
     * @param name    represents the key of the image to be sharpened.
     * @param newName represents the key of the new sharpened image.
     */
    @Override
    public void sharpen(String name, String newName) {
      this.log.append("sharpen ")
              .append(name)
              .append("and save it as '")
              .append(newName)
              .append("'\n");

    }

    /**
     * Method to greyScale Luma an image using the color transformation matrices. Takes in an image.
     * With key 'keyName' and places the new image with key 'newName' into the imageLst hashmap.
     *
     * @param keyName represents the key in the hashmap of the image to be greyscaled.
     * @param newName represents the key in the hashmap of the new greyscaled image.
     */
    @Override
    public void greyscaleLuma(String keyName, String newName) {
      this.log.append("greyScaleLuma ")
              .append(keyName)
              .append("and save it as '")
              .append(newName)
              .append("'\n");
    }

    /**
     * Method to sepia transform an image using the color transformation matrices.
     * Takes in an image.
     * With key 'keyName' and places the new image with key 'newName' into the imageLst hashmap.
     *
     * @param keyName represents the key in the hashmap of the image to be sepia transform.
     * @param newName represents the key in the hashmap of the new sepia transformed image.
     */
    @Override
    public void sepia(String keyName, String newName) {
      this.log.append("sepia ")
              .append(keyName)
              .append("and save it as '")
              .append(newName)
              .append("'\n");

    }

    /**
     * Method to downsize an Image by a percent specified by the client.
     * Takes in a key for the image to be downsized and places a new downsized image in the hashmap.
     * With key 'newKey'
     *
     * @param key     represents the key of the old image.
     * @param newKey  represents the key of the new image.
     * @param percent represents the percent to which the image should be downsized.
     * @throws IllegalArgumentException if the specified percent is invalid.
     */
    @Override
    public void downsize(String key, String newKey, int percent) throws IllegalArgumentException {
      this.log.append("downsize button works");
    }
  }


  protected static class MockImageProcessingSwingView implements GuiViewInterface {
    private final StringBuilder log;

    public MockImageProcessingSwingView(StringBuilder log) {
      this.log = log;
    }

    /**
     * Method to display the current image being edited on.
     * This takes an image from the controller and displays this image on the image label.
     * This method allows the user to see the current image that is being worked out.
     *
     * @param image represents the image to be displayed.
     */
    @Override
    public void displayImage(Image image) {
      this.log.append("display image");
    }

    /**
     * Mehtod to display the Histogram image on the hist panel.
     *
     * @param image Buffered Image that is to be displayed.
     */
    @Override
    public void displayHistogram(BufferedImage image) {

      this.log.append("display histogram");
    }

    /**
     * Method to display a pop-up in the view to ask the user to enter an increment.
     * this is used in features like brighten and darken where user input in required.
     *
     * @return returns the integer value of the user input.
     */
    @Override
    public int getIncrement() {
      return 0;
    }

    /**
     * Method to display a pop-up in the view to ask the user to enter an increment.
     * this is used in features like brighten and darken where user input in required.
     *
     * @return returns the integer value of the user input.
     */
    @Override
    public int getPercentage() {
      return 0;
    }

    /**
     * Method to display a pop-up error message if there is an invalid user input.
     *
     * @param error error message to be displayed.
     */
    @Override
    public void displayError(String error) {
      this.log.append("display error");

    }

    /**
     * This method sets the action listener.
     * Allows for the controller to be the action listener.
     *
     * @param listener represents an action listener.
     */
    @Override
    public void setListener(ActionListener listener) {
      this.log.append("adds the given action listener to this view");
    }
  }
}