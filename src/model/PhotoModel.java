package model;

import java.util.Map;

/**
 * This class represents the model for Model.PhotoEditor, it contains all Model.
 * Image editing related functions.
 */
public class PhotoModel implements PhotoEditor {
  private Map<String, Image> imageList;

  /**
   * Constructor to instantiate the photoModel class.
   *
   * @param imageList Hashmap that stores images worked on in this model.
   */
  public PhotoModel(Map<String, Image> imageList) {
    this.imageList = imageList;
  }

  /**
   * Method to flip an image horizontally.
   * Then store the flipped Model.Image in the model Hashmap with key as the 'newFile' string.
   */
  @Override
  public void flipHorizontally(String file, String newFile) {
    Image oldImage = this.imageList.get(file);
    Pixel[][] flip = oldImage.getImage();

    for (int i = 0; i < oldImage.getHeight(); i++) {
      for (int j = 0; j < oldImage.getWidth() / 2; j++) {
        Pixel temp = flip[i][j];
        flip[i][j] = flip[i][oldImage.getWidth() - j - 1];
        flip[i][oldImage.getWidth() - j - 1] = temp;
      }
    }
    Image verticalImage = new Image(oldImage.getWidth(), oldImage.getHeight(),
            flip, oldImage.getMax());
    this.imageList.put(newFile, verticalImage);
  }

  /**
   * Method to flip an image vertically.
   * Then store the flipped Model.Image in the model Hashmap with key as the 'newFile' string.
   */
  @Override
  public void flipVertically(String file, String newFile) {
    Image oldImage = this.imageList.get(file);
    Pixel[][] flip = oldImage.getImage();
    for (int i = 0; i < oldImage.getHeight() / 2; i++) {
      for (int j = 0; j < oldImage.getWidth(); j++) {
        Pixel temp = flip[i][j];
        flip[i][j] = flip[oldImage.getHeight() - i - 1][j];
        flip[oldImage.getHeight() - i - 1][j] = temp;
      }
    }
    Image verticalImage = new Image(oldImage.getWidth(), oldImage.getHeight(),
            flip, oldImage.getMax());
    this.imageList.put(newFile, verticalImage);
  }

  /**
   * method to Brighten an image.
   * Then store the brightened Model.Image in the model Hashmap with key as the 'newFile' string.
   *
   * @param increment amount to be incremented by.
   * @param newFile   key to the newImage in the hashmap.
   */
  @Override
  public void brighten(String file, int increment, String newFile) {
    Image oldImage = this.imageList.get(file);
    Image newImage = brightenDarkenHelper(increment, oldImage);
    imageList.put(newFile, newImage);
  }

  /**
   * method to darken an image.
   * Then store the darkened Model.Image in the model Hashmap with key as the 'newFile' string.
   *
   * @param file      represents the key to image to be darkened in the hashMap
   * @param increment the amount to increase.
   * @param newFile   the key to the new image.
   */
  @Override
  public void darken(String file, int increment, String newFile) {
    Image oldImage = this.imageList.get(file);
    Image newImage = brightenDarkenHelper(increment, oldImage);
    imageList.put(newFile, newImage);
  }

  /**
   * Method to greyScale an Image.
   *
   * @param name     key to Image to be greyScaled.
   * @param fileName key to greyScaled image.
   * @param type     type of GreyScale from greyScale enum.
   */
  @Override
  public void greyScale(String name, String fileName, GreyScaleEnum type) {
    Image oldImage = this.imageList.get(name);
    switch (type) {
      case RED:
        Image newImageRed = greyScaleColor("red", oldImage);
        imageList.put(fileName, newImageRed);
        break;
      case GREEN:
        Image newImageGreen = greyScaleColor("green", oldImage);
        imageList.put(fileName, newImageGreen);
        break;
      case BLUE:
        Image newImageBlue = greyScaleColor("blue", oldImage);
        imageList.put(fileName, newImageBlue);
        break;
      case VALUE:
        Image newImageValue = greyScaleValue(oldImage);
        imageList.put(fileName, newImageValue);
        break;
      case INTENSITY:
        Image newImageIntensity = greyScaleIntensity(oldImage);
        imageList.put(fileName, newImageIntensity);
        break;

      case LUMA:
        Image newImageLuma = greyScaleLuma(oldImage);
        imageList.put(fileName, newImageLuma);
        break;

      default:
        throw new IllegalArgumentException("Illegal enum type");
    }
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
    return this.imageList.get(key);
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
    this.imageList.put(key, image);
  }

  // helper method to brighten and darken an image.
  private Image brightenDarkenHelper(int increment, Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int max = image.getMax();
    Pixel[][] pixelList = image.getImage();
    Pixel[][] newPicture = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = brightenHelper(pixelList[i][j].getRed(), increment, max);
        int green = brightenHelper(pixelList[i][j].getGreen(), increment, max);
        int blue = brightenHelper(pixelList[i][j].getBlue(), increment, max);

        newPicture[i][j] = new Pixel(red, green, blue);
      }
    }
    Image newImage = new Image(width, height, newPicture, max);
    return newImage;
  }

  // calculates what the color value should be for brighten or darken.
  private int brightenHelper(int value, int increment, int max) {
    if ((value + increment) > max) {
      return max;
    } else if ((value + increment) < 0) {
      return 0;
    } else {
      return value + increment;

    }
  }

  // helper to greyScale to a color.
  private Image greyScaleColor(String color, Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int max = image.getMax();
    Pixel[][] pixelList = image.getImage();
    Pixel[][] newPicture = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int colorVal = pixelList[i][j].getColor(color);
        newPicture[i][j] = new Pixel(colorVal, colorVal, colorVal);
      }
    }
    Image newImage = new Image(width, height, newPicture, max);
    return newImage;
  }

  // helper to greyScale to a value.
  private Image greyScaleValue(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int max = image.getMax();
    Pixel[][] pixelList = image.getImage();
    Pixel[][] newPicture = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int colorVal = pixelList[i][j].getMaxComponent();
        newPicture[i][j] = new Pixel(colorVal, colorVal, colorVal);
      }
    }
    Image newImage = new Image(width, height, newPicture, max);
    return newImage;
  }

  // helper to greyScale to an intensity.
  private Image greyScaleIntensity(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int max = image.getMax();
    Pixel[][] pixelList = image.getImage();
    Pixel[][] newPicture = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int colorVal = (int) Math.round(pixelList[i][j].getAvgValue());
        newPicture[i][j] = new Pixel(colorVal, colorVal, colorVal);
      }
    }
    Image newImage = new Image(width, height, newPicture, max);
    return newImage;
  }

  // helper to greyScale to Luma.
  private Image greyScaleLuma(Image image) {
    int height = image.getHeight();
    int width = image.getWidth();
    int max = image.getMax();
    Pixel[][] pixelList = image.getImage();
    Pixel[][] newPicture = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int colorVal = (int) Math.round(pixelList[i][j].getLumaValue());
        newPicture[i][j] = new Pixel(colorVal, colorVal, colorVal);
      }
    }
    Image newImage = new Image(width, height, newPicture, max);
    return newImage;
  }

  // Helper method to apply a filter to an Image.
  // returns an Image with the filter applied on it.
  private Image applyFilter(double[][] kernel, Image image) {

    int height = image.getHeight();
    int width = image.getWidth();
    Pixel[][] pixelGrid = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      Pixel[] tempRow = new Pixel[width];
      for (int j = 0; j < width; j++) {

        int filteredRed = checkBounds((this.applyKernel(kernel, i, j, "r", image)), image);
        int filteredGreen = checkBounds((this.applyKernel(kernel, i, j, "g", image)), image);
        int filteredBlue = checkBounds((this.applyKernel(kernel, i, j, "b", image)), image);
        Pixel filteredPixel = new Pixel(filteredRed, filteredGreen, filteredBlue);
        tempRow[j] = filteredPixel;
      }
      pixelGrid[i] = tempRow;
    }

    Image newImage = new Image(width, height, pixelGrid, 225);
    return newImage;
  }

  // method to apply the kernel filter to a pixel color val.
  private int applyKernel(double[][] kernel, int row, int col, String color, Image image) {
    int centerSlot = (kernel.length - 1) / 2;
    int newPixelVal = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel.length; j++) {

        if (!(row - centerSlot + i < 0 || row - centerSlot + i >= image.getHeight())
                && !(col - centerSlot + j < 0 || col - centerSlot + j >= image.getWidth())) {
          double kernelVal = kernel[i][j];
          double pixelGridVal = 0;

          if (color.equals("r")) {
            pixelGridVal += image.getPixel(row - centerSlot + i, col - centerSlot + j).getRed();

          }
          if (color.equals("g")) {
            pixelGridVal += image.getPixel(row - centerSlot + i, col - centerSlot + j).getGreen();
          }
          if (color.equals("b")) {
            pixelGridVal += image.getPixel(row - centerSlot + i, col - centerSlot + j).getBlue();
          }
          newPixelVal += pixelGridVal * kernelVal;
        }
      }
    }

    return newPixelVal;
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
    Image image = this.imageList.get(name);
    double[][] kernel = new double[][]{new double[]{0.0625, 0.125, 0.0625},
                                       new double[]{0.125, 0.25, 0.125},
                                       new double[]{0.0625, 0.125, 0.0625}
    };
    Image newImage = this.applyFilter(kernel, image);
    this.imageList.put(newName, newImage);

  }

  /**
   * Method to sharpen an Image with 'name' and put the sharpened image into the imageList Hashmap.
   * With the key 'newName'.
   *
   * @param name    represents the key of the image to be sharpened.
   * @param newName represents the key of the new sharpened image.
   */
  @Override
  public void sharpen(String name, String newName) {
    Image image = this.imageList.get(name);
    double[][] kernel = new double[][]{new double[]{-0.125, -0.125, -0.125, -0.125, -0.125},
                                       new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
                                       new double[]{-0.125, 0.25, 1, 0.25, -0.125},
                                       new double[]{-0.125, 0.25, 0.25, 0.25, -0.125},
                                       new double[]{-0.125, -0.125, -0.125, -0.125, -0.125}
    };
    Image newImage = this.applyFilter(kernel, image);
    this.imageList.put(newName, newImage);

  }

  // Helper method to apply a color transformation on an Image.
  private Image applyColorTransformation(double[][] matrix, Image image) {
    int height = image.getHeight();
    int width = image.getWidth();

    Pixel[][] copyGrid = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Pixel oldPixel = image.getPixel(i, j);
        int newRed = checkBounds(((int) (matrix[0][0] * oldPixel.getRed() + matrix[0][1]
                * oldPixel.getGreen()
                + matrix[0][2] * oldPixel.getBlue())), image);
        int newGreen = checkBounds(((int) (matrix[1][0] * oldPixel.getRed() + matrix[1][1]
                * oldPixel.getGreen()
                + matrix[1][2] * oldPixel.getBlue())), image);
        int newBlue = checkBounds(((int) (matrix[2][0] * oldPixel.getRed() + matrix[2][1]
                * oldPixel.getGreen()
                + matrix[2][2] * oldPixel.getBlue())), image);
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue);
        copyGrid[i][j] = newPixel;
      }
    }
    return new Image(width, height, copyGrid, image.getMax());
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
    Image image = this.imageList.get(keyName);
    double[][] matrix = new double[][]{new double[]{0.2126, 0.7152, 0.0722},
                                       new double[]{0.2126, 0.7152, 0.0722},
                                       new double[]{0.2126, 0.7152, 0.0722}
    };
    Image newImage = this.applyColorTransformation(matrix, image);
    this.imageList.put(newName, newImage);

  }

  /**
   * Method to sepia transform an image using the color transformation matrices. Takes in an image.
   * With key 'keyName' and places the new image with key 'newName' into the imageLst hashmap.
   *
   * @param keyName represents the key in the hashmap of the image to be sepia transform.
   * @param newName represents the key in the hashmap of the new sepia transformed image.
   */
  @Override
  public void sepia(String keyName, String newName) {
    Image image = this.imageList.get(keyName);
    double[][] matrix = new double[][]{new double[]{0.393, 0.769, 0.189},
                                       new double[]{0.349, 0.686, 0.168},
                                       new double[]{0.272, 0.534, 0.131}
    };
    Image newImage = this.applyColorTransformation(matrix, image);
    this.imageList.put(newName, newImage);
  }

  private int checkBounds(int num, Image image) {
    int max = image.getMax();

    if (num > max) {
      return max;
    } else if (num < 0) {
      return 0;
    } else {
      return num;
    }
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
  public void downsize(String key, String newKey, int percent) throws IllegalArgumentException {
    Image image = this.getImage(key);
    int width = image.getWidth();
    int height = image.getHeight();
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Percent invalid");
    }

    int newWidth = (width * (100 - percent)) / 100;
    int newHeight = (height * (100 - percent)) / 100;

    Pixel[][] newPixelGrid = new Pixel[newHeight][newWidth];

    for (int i = 0; i < newHeight; i++) {
      Pixel[] tempRow = new Pixel[newWidth];
      for (int j = 0; j < newWidth; j++) {
        Pixel pixelMap = this.pixelMap(i, j, newWidth, newHeight, image);
        tempRow[j] = pixelMap;
      }
      newPixelGrid[i] = tempRow;
    }
    Image newImage = new Image(newWidth, newHeight, newPixelGrid, image.getMax());
    this.imageList.put(newKey, newImage);
  }

  private Pixel pixelMap(int row, int col, int newWidth, int newHeight, Image image) {

    double oldX = image.getWidth() * ((double) row / (double) newWidth);

    double oldY = ((double) col / (double) newHeight) * image.getHeight();


    if (oldX % 1 != 0
            || oldY % 1 != 0) {
      return combinePixel((int) oldX, (int) oldY, image);
    }

    Pixel oldPixel = image.getPixel((int) oldX, (int) oldY);

    return new Pixel(oldPixel.getRed(), oldPixel.getGreen(), oldPixel.getBlue());
  }

  private Pixel combinePixel(int row, int col, Image image) {
    int red = 0;

    int green = 0;

    int blue = 0;

    int avg = 4;

    try {
      Pixel pixel = image.getPixel(row - 1, col);
      red += pixel.getRed();
      green += pixel.getGreen();
      blue += pixel.getBlue();
    } catch (IndexOutOfBoundsException e) {
      avg -= 1;
    }

    try {
      Pixel downPixel = image.getPixel(row + 1, col);
      red += downPixel.getRed();
      green += downPixel.getGreen();
      blue += downPixel.getBlue();
    } catch (IndexOutOfBoundsException e) {
      avg -= 1;
    }

    try {
      Pixel leftPixel = image.getPixel(row, col - 1);
      red += leftPixel.getRed();
      green += leftPixel.getGreen();
      blue += leftPixel.getBlue();
    } catch (IndexOutOfBoundsException e) {
      avg -= 1;
    }

    try {
      Pixel rightPixel = image.getPixel(row, col + 1);
      red += rightPixel.getRed();
      green += rightPixel.getGreen();
      blue += rightPixel.getBlue();
    } catch (IndexOutOfBoundsException e) {
      avg -= 1;
    }

    return new Pixel(red / avg, green / avg, blue / avg);
  }
}
