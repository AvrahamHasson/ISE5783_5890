package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * An abstract class Representing a light source
 *
 * @author Avraham Hassson
 */
public interface LightSource {
    /**
     * Retrieves the color intensity IL, the color intensity of the light at the given point(IL).
     *
     * @param p the point at which to calculate the color intensity
     * @return the color intensity of the light at the given point(IL).
     */
    public Color getIntensity(Point p);
    /**
     * Retrieves the direction vector from the light source to the given point.
     *
     * @param p the point at which to calculate the direction vector
     * @return the direction vector from the light source to the given point
     */
    public Vector getL(Point p);
}
