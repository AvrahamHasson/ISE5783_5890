package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A specific type of light source representing a point light.
 * It emits light uniformly in all directions from a specific position.
 *
 * @author Avraham Hassson
 */
public class PointLight extends Light implements LightSource{
    /**
     * The light position.
     */
    private final Point position;
    private double kC=1, kL=0, kQ=0;
    /**
     * Constructs a point light with the specified intensity and position.
     *
     * @param intensity the color intensity of the point light
     * @param position the position of the point light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }
    /**
     * Sets the constant attenuation factor of the point light.
     * @param kC the constant attenuation factor
     * @return the updated point light object
     */
    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }
    /**
     * Sets the linear attenuation factor of the point light.
     * @param kL the linear attenuation factor
     * @return the updated point light object
     */
    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }
    /**
     * Sets the quadratic attenuation factor of the point light.
     * @param kQ the quadratic attenuation factor
     * @return the updated point light object
     */
    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double distance= position.distance(p);
        return getIntensity().scale(1/(kC+kL*distance+kQ*distance*distance));
    }

    @Override
    public Vector getL(Point p) {
        return (p.subtract(position)).normalize();
    }
}
