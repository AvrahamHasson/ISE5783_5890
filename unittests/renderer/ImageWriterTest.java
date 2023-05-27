package renderer;

import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;

/**
 * A test class for the ImageWriter class.
 *
 * @author Avraham Hasson
 */
class ImageWriterTest {

    /**
     * Tests the functionality of the ImageWriter class by creating an image with
     * specified dimensions and writing pixels with alternating colors.
     */
    @Test
    void testImageWriter() {
        // Create a new ImageWriter object with specified dimensions
        ImageWriter images = new ImageWriter("images", 800, 500);

        // Set the interval for alternating colors
        int x = 50;

        // Loop through the image pixels and write alternating colors
        for (int i = 0; i < images.getNx(); i++) {
            for (int j = 0; j < images.getNy(); j++) {
                // Set color based on the interval
                Color color = (i % x == 0 || j % x == 0) ? new Color(0, 0, 255) : new Color(255, 0, 0);
                images.writePixel(i, j, color);
            }
        }

        // Write the image to file
        images.writeToImage();
    }
}
