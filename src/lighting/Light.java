package lighting;

import primitives.Color;

/**
 * An abstract class Representing a light source
 *
 * @author Avraham Hassson
 */
public abstract class Light {
    /**
     * Represents The intensity of the light.
     */
    private final Color intensity;

    /**
     * Getter for the intensity.
     * @return The intensity.
     */
    public Color getIntensity() {
        return intensity;
    }

    /**
     * Constructs a Light object with a given intensity.
     *
     * @param intensity The Light intensity.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }
}
