import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import controller.ControllerImageImpl;
import model.GreyScaleEnum;
import model.Image;
import model.PhotoModel;
import model.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * represents the class used in testing the photoModel.
 */
public class PhotoModelTest {


  // pixel by pixel test for a self made 2 * 2 pixel grid.
  @Test
  public void testBrightenWith9Pixel() {
    PhotoModel m = new PhotoModel((new HashMap<String, Image>()));
    Pixel one = new Pixel(100, 200, 300);
    Pixel two = new Pixel(200, 100, 300);
    Pixel three = new Pixel(300, 200, 100);
    Pixel[][] pixelList = new Pixel[2][2];
    pixelList[0][0] = one;
    pixelList[0][1] = two;
    pixelList[1][0] = one;
    pixelList[1][1] = two;
    System.out.println(pixelList[0][0].getRed());
    Image sampleImage = new Image(2, 2, pixelList, 500);
    m.putImage("sample", sampleImage);
    System.out.println(pixelList[0][0].getRed());
    m.brighten("sample", 100, "sampleNew");
    Pixel[][] newPixelList = m.getImage("sampleNew").getImage();

    System.out.println(pixelList[0][0].getRed());
    System.out.println(newPixelList[0][0].getRed());
    //check if red of first pixel increased by 100
    assertEquals(200, newPixelList[0][0].getRed());
    assertEquals(300, newPixelList[0][0].getGreen());
    assertEquals(400, newPixelList[0][0].getBlue());

    assertEquals(300, newPixelList[0][1].getRed());
    assertEquals(200, newPixelList[0][1].getGreen());
    assertEquals(400, newPixelList[0][1].getBlue());

    assertEquals(200, newPixelList[1][0].getRed());
    assertEquals(300, newPixelList[1][0].getGreen());
    assertEquals(400, newPixelList[1][0].getBlue());

    assertEquals(300, newPixelList[1][1].getRed());
    assertEquals(200, newPixelList[1][1].getGreen());
    assertEquals(400, newPixelList[1][1].getBlue());
  }


  // testing the brighten function
  @Test
  public void testBrightenWithImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel", "res/4PixelImage.ppm");
    m.brighten("fourPixel", 100, "fourPixelBright");
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelBright");
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

  // testing the Darken function
  @Test
  public void testDarkenWithImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel", "res/4PixelImage.ppm");
    m.darken("fourPixel", -100, "fourPixelDark");
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelDark");
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


  // testing the Darken function
  @Test
  public void testDarkenThenBrightenWithImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel", "res/4PixelImage.ppm");
    m.brighten("fourPixel", 100, "fourPixelDark");
    m.darken("fourPixelDark", -100, "brightDark");
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("brightDark");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());
        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());
      }
    }
  }

  // testing the GreyScale functions
  @Test
  public void testGreyScaleRedImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel", "res/4PixelImage.ppm");
    m.greyScale("fourPixel", "fourPixelGreyRed", GreyScaleEnum.RED);
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the GreyScale functions
  @Test
  public void testGreyScaleGreenImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    m.greyScale("fourPixel", "fourPixelGreyRed", GreyScaleEnum.GREEN);
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the GreyScale functions
  @Test
  public void testGreyScaleBlueImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel", "res/4PixelImage.ppm");
    m.greyScale("fourPixel", "fourPixelGreyRed", GreyScaleEnum.BLUE);
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the GreyScale functions
  @Test
  public void testGreyScaleValueImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    m.greyScale("fourPixel", "fourPixelGreyRed", GreyScaleEnum.VALUE);
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the GreyScale functions
  @Test
  public void testGreyScaleIntensityImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    m.greyScale("fourPixel", "fourPixelGreyRed", GreyScaleEnum.INTENSITY);
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the GreyScale functions
  @Test
  public void testGreyScaleLumaImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    m.greyScale("fourPixel", "fourPixelGreyRed", GreyScaleEnum.LUMA);
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the Flip functions
  @Test
  public void testFlipImageHorizontally() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    m.flipHorizontally("fourPixel", "fourPixelGreyRed");
    Image oldImage = m.getImage("fourPixel");
    Image newImage = m.getImage("fourPixelGreyRed");
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

  // testing the Flip functions
  @Test
  public void testFlipImageVertically() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    Image oldImage = m.getImage("fourPixel");
    m.flipVertically("fourPixel", "fourPixelGreyRed");
    Image newImage = m.getImage("fourPixelGreyRed");

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

  // testing the load function
  @Test
  public void testLoad() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel",
            "res/4PixelImage.ppm");
    Image oldImage = m.getImage("fourPixel");
    assertEquals(3, oldImage.getHeight());
    assertEquals(4, oldImage.getWidth());

  }

  // test Save
  @Test
  public void testBrightenWithImage2() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("fourPixel", "res/TDgardenPhoto.ppm");
    m.brighten("fourPixel", 100, "fourPixelBright");
    controller.saveImage("res/TDgardenPhotoBrightTest.ppm", "fourPixelBright");
    controller.load("newTDPIC", "res/TDgardenPhotoBrightTest.ppm");
    Image old = m.getImage("fourPixel");
    Image newImage = m.getImage("newTDPIC");
    assertEquals(old.getHeight(), newImage.getHeight());

  }

  // testing loading and saving jpeg.
  @Test
  public void testJpegImage() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("4pix", "res/4PixelImage.jpg");
    controller.saveImage("res/updated4pix.jpg", "4pix");
    controller.load("4pix2", "res/updated4pix.jpg");
    Image newImage = m.getImage("4pix2");
    Image oldImage = m.getImage("4pix");
    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertTrue((Math.abs(newImage.getPixel(i, j).getRed()
                - oldImage.getPixel(i, j).getRed())) < 20);

        assertTrue((Math.abs(newImage.getPixel(i, j).getGreen()
                - oldImage.getPixel(i, j).getGreen())) < 20);

        assertTrue((Math.abs(newImage.getPixel(i, j).getBlue()
                - oldImage.getPixel(i, j).getBlue())) < 20);
      }
    }

  }

  @Test
  public void testPNGImage2() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("kyrie", "res/TDgardenPhoto.png");
    controller.saveImage("res/TDgardenPhoto2.png", "kyrie");
    controller.load("kyrie2", "res/TDgardenPhoto2.png");
    Image newImage = m.getImage("kyrie2");
    Image oldImage = m.getImage("kyrie");

    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {

        assertEquals(newImage.getPixel(i, j).getRed(),
                oldImage.getPixel(i, j).getRed());

        assertEquals(newImage.getPixel(i, j).getGreen(),
                oldImage.getPixel(i, j).getGreen());

        assertEquals(newImage.getPixel(i, j).getBlue(),
                oldImage.getPixel(i, j).getBlue());
      }
    }

  }

  @Test
  public void testSEPIAonPNG() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("kyrie", "res/TDgardenPhoto.png");
    m.sepia("kyrie", "kyrie3");
    controller.saveImage("testerImages/TDgardenPhoto2.png", "kyrie3");
    controller.load("kyrie2", "testerImages/TDgardenPhoto2.png");
    Image newImage = m.getImage("kyrie2");
    Image oldImage = m.getImage("kyrie3");

    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(oldImage.getHeight(), oldImage.getHeight());
        assertEquals(newImage.getWidth(), oldImage.getWidth());

        assertEquals(newImage.getPixel(i, j).getRed(),
                oldImage.getPixel(i, j).getRed());

        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());

        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
      }
    }

  }

  @Test
  public void testSharpenonPNG() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("kyrie", "res/TDgardenPhoto.png");
    m.sharpen("kyrie", "kyrie3");
    controller.saveImage("testerImages/TDgardenPhoto2.png", "kyrie3");
    controller.load("kyrie2", "testerImages/TDgardenPhoto2.png");
    Image newImage = m.getImage("kyrie2");
    Image oldImage = m.getImage("kyrie3");

    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(newImage.getHeight(), oldImage.getHeight());
        assertEquals(newImage.getWidth(), oldImage.getWidth());

        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());

        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());

        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testBlurPNG() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("kyrie", "res/TDgardenPhoto.png");
    m.blur("kyrie", "kyrie3");
    controller.saveImage("testerImages/TDgardenPhoto2.png", "kyrie3");
    controller.load("kyrie2", "testerImages/TDgardenPhoto2.png");
    Image newImage = m.getImage("kyrie2");
    Image oldImage = m.getImage("kyrie3");

    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(newImage.getHeight(), oldImage.getHeight());
        assertEquals(newImage.getWidth(), oldImage.getWidth());

        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());

        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());

        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
      }
    }
  }

  @Test
  public void testgreyScalePNG() throws IOException {
    PhotoModel m = new PhotoModel(new HashMap<String, Image>());
    ControllerImageImpl controller = new ControllerImageImpl(m);
    controller.load("kyrie", "res/TDgardenPhoto.png");
    m.greyscaleLuma("kyrie", "kyrie3");
    controller.saveImage("testerImages/TDgardenPhoto2.png", "kyrie3");
    controller.load("kyrie2", "testerImages/TDgardenPhoto2.png");
    Image newImage = m.getImage("kyrie2");
    Image oldImage = m.getImage("kyrie3");

    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        assertEquals(newImage.getHeight(), oldImage.getHeight());
        assertEquals(newImage.getWidth(), oldImage.getWidth());

        assertEquals(oldImage.getPixel(i, j).getRed(),
                newImage.getPixel(i, j).getRed());

        assertEquals(oldImage.getPixel(i, j).getGreen(),
                newImage.getPixel(i, j).getGreen());

        assertEquals(oldImage.getPixel(i, j).getBlue(),
                newImage.getPixel(i, j).getBlue());
      }
    }
  }
}
