package lighting;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A directional light source that emits light uniformly in a specific direction.
 *
 * @author Avraham Hassson
 */
public class DirectionalLight extends Light implements LightSource {
    /**
     * The light direction.
     */
    private final Vector direction;
    /**
     * Constructs a directional light with the specified intensity and direction.
     *
     * @param intensity the color intensity of the light
     * @param direction the direction in which the light is emitted, normalized.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }
}

