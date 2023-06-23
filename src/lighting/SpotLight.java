package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * A spotlight is a type of light source that emits light in a specific direction from a point in space.
 * It is a subclass of the PointLight class.
 *
 * @author Avraham Hassson
 */
public class SpotLight extends PointLight{

    /**
     * affects the beam width
     */
    private double narrowBeamFactor = 1;

    /**
     * The light direction.
     */
    private final Vector direction;
    /**
     * Constructs a spotlight with the specified intensity, position, and direction.
     *
     * @param intensity the color intensity of the spotlight
     * @param position the position of the spotlight in 3D space
     * @param direction the normalized direction in which the spotlight emits light
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }
    /**
     * Sets the narrow beam factor for the spotLight.
     *
     * @param narrow the narrow beam factor to set
     * @return the updated SpotLight object
     */
    public SpotLight setNarrowBeam(double narrow) {
        narrowBeamFactor = narrow;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double lDir = getL(p).dotProduct(direction);
        return lDir < 0 ? Color.BLACK : super.getIntensity(p).scale(Math.pow(lDir,narrowBeamFactor));
    }
}
