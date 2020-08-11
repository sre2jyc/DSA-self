import java.awt.Color;
import java.awt.Image;

public class ColorSeparation {

    public static void main(String[] args) {
        
        // read in the picture specified by command-line argument
        Picture picture = new Picture(args[0]);
        int width  = picture.width();
        int height = picture.height();

        // create three empy pictures of the same dimension
        Picture pictureR = new Picture(width, height);
        Picture pictureG = new Picture(width, height);
        Picture pictureB = new Picture(width, height);

        // separate colors
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                Color color = picture.get(col, row);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();
                pictureR.set(col, row, new Color(r, 0, 0));
                pictureG.set(col, row, new Color(0, g, 0));
                pictureB.set(col, row, new Color(0, 0, b));
            }
        }

        // display each picture in its own window
        pictureR.show();
        pictureG.show();
        pictureB.show();
    }
 
   
}
