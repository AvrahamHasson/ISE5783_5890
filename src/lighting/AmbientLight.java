package lighting;

import primitives.Color;
import primitives.Double3;
/**
 * Represents an ambient light source
 *
 * @author Avraham Hassson
 */
public class AmbientLight {
    /**
     * Represents a constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    /**
     * Represents The intensity of the ambient light.
     */
    private final Color intensity;
    /**
     * Constructs an AmbientLight object with the given intensity and attenuation factor.
     *
     * @param iA The intensity of the ambient light.
     * @param kA        The attenuation factor of the ambient light.
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }
    /**
     * Retrieves the intensity of the ambient light.
     *
     * @return The intensity of the ambient light.
     */
    public Color getIntensity() {
        return this.intensity;
    }

}
