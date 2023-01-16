import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import controller.ControllerImageImpl;
import model.Image;
import model.PhotoModel;
import view.ImageView;
import static org.junit.Assert.assertEquals;

/**
 * represents the class used to test the image controller.
 */
public class ControllerImageImplTest {

  // test loading an image with the controller and brightening the image.
  @Test
  public void testBrightenRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "brighten fourPixel 100 fourPixelBright\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelBright");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(oldImage.getPixel(i, j).getRed() + 100,
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue() + 100,
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen() + 100,
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and Darkening the image.
  @Test
  public void testDarkenRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "darken fourPixel -100 fourPixelDark\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelDark");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(Math.max(oldImage.getPixel(i, j).getRed() - 100, 0),
                newImage.getPixel(i, j).getRed());
        assertEquals(Math.max(oldImage.getPixel(i, j).getBlue() - 100, 0),
                newImage.getPixel(i, j).getBlue());
        assertEquals(Math.max(oldImage.getPixel(i, j).getGreen() - 100, 0),
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and greyScale red the Image.
  @Test
  public void testGreyScaleRedRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "redGreyscale fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and greyScaleGreen the image.
  @Test
  public void testGreyScaleGreenRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "greenGreyscale fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and greyScaleBlue the image.
  @Test
  public void testGreyScaleBlueRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "blueGreyscale fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and greyScaleIntensity the image.
  @Test
  public void testGreyScaleIntensityRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "intensityGreyscale fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        int maxPixelColor = (int) Math.round(oldImage.getPixel(i, j).getAvgValue());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getRed());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getBlue());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and greyScaleValue the image.
  @Test
  public void testGreyScaleValueRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "valueGreyscale fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        int maxPixelColor = oldImage.getPixel(i, j).getMaxComponent();
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getRed());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getBlue());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading an image with the controller and greyScaleLuma the image.
  @Test
  public void testGreyScaleLumaRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "lumaGreyscale fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        int maxPixelColor = (int) Math.round(oldImage.getPixel(i, j).getLumaValue());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getRed());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getBlue());
        assertEquals(maxPixelColor,
                newImage.getPixel(i, j).getGreen());
      }
    }
  }


  // test loading an image with the controller and greyScaleLuma the image.
  @Test
  public void testGreyScaleVerticallyRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "verticalFlip fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {

        assertEquals(newImage.getPixel(i, j).getRed(),
                oldImage.getPixel(oldImage.getHeight() - i - 1, j).getRed());

        assertEquals(newImage.getPixel(i, j).getGreen(),
                oldImage.getPixel(oldImage.getHeight() - i - 1, j).getGreen());

        assertEquals(newImage.getPixel(i, j).getBlue(),
                oldImage.getPixel(oldImage.getHeight() - i - 1, j).getBlue());
      }
    }
  }

  // test loading an image with the controller and greyScaleLuma the image.
  @Test
  public void testGreyScaleHorizontallyRun() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/4PixelImage.ppm\n" +
            "horizontalFlip fourPixel fourPixelGreyRed\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelGreyRed");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(newImage.getPixel(i, j).getRed(),
                oldImage.getPixel(i, oldImage.getWidth() - j - 1).getRed());
        assertEquals(newImage.getPixel(i, j).getGreen(),
                oldImage.getPixel(i, oldImage.getWidth() - j - 1).getGreen());
        assertEquals(newImage.getPixel(i, j).getBlue(),
                oldImage.getPixel(i, oldImage.getWidth() - j - 1).getBlue());
      }
    }
  }

  // test loading a png image with the controller and brightening the image.
  @Test
  public void testBrightenRunPNG() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/TDgardenPhoto.png\n" +
            "brighten fourPixel 100 fourPixelBright\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixel");
    Image newImage = model.getImage("fourPixelBright");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(oldImage.getPixel(i, j).getRed() + 100,
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue() + 100,
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen() + 100,
                newImage.getPixel(i, j).getGreen());
      }
    }

  }


  // test loading a png image with the controller and brightening the image.
  // and checking
  @Test
  public void testBrightenRunPNG2() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/TDgardenPhoto.png\n" +
            "brighten fourPixel 100 fourPixelBright\n" +
            "save testerImages/TDgardenPhoto2.png fourPixelBright\n" +
            "load TDpng testerImages/TDgardenPhoto2.png\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixelBright");
    Image newImage = model.getImage("TDpng");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // test loading a bmp image with the controller and sepia the image.
  // and checking
  @Test
  public void testLoadandSave2() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/TDgardenPhoto.bmp\n" +
            "sepia fourPixel fourPixelBright\n" +
            "save testerImages/TDgardenPhoto2.bmp fourPixelBright\n" +
            "load TDpng testerImages/TDgardenPhoto2.bmp\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixelBright");
    Image newImage = model.getImage("TDpng");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());
      }
    }
  }

  // test loading with a jpeg, saving with ppm image with the controller and greyscale the image.
  // and checking
  @Test
  public void testLoadandSave3() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("load fourPixel res/TDgardenPhoto.jpg\n" +
            "greyscale fourPixel fourPixelBright\n" +
            "save testerImages/TDgardenPhoto2.ppm fourPixelBright\n" +
            "load TDpng testerImages/TDgardenPhoto2.ppm\n" +
            "q");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    Image oldImage = model.getImage("fourPixelBright");
    Image newImage = model.getImage("TDpng");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());
      }
    }

  }

  // testing for bad input.
  @Test
  public void testbadInput() throws IOException {
    Appendable appendable = new StringBuilder();
    Readable readable = new StringReader("loader res/badinput bsdin");
    ImageView view = new ImageView(appendable);
    PhotoModel model = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(readable, model, view);
    controller.runProgram();
    assertEquals(appendable.toString(),
            "try again, an invalid command was entered\n");
  }
}
