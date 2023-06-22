package primitives;
/**
 * The Material class represents the material properties of an object.
 *
 * @author Avraham Hassson
 */
public class Material {
    /**
     * The diffuse reflection coefficient of the material
     */
    public Double3 kD=Double3.ZERO;

    /**
     * The specular reflection coefficient of the material.
     */
    public Double3 kS=Double3.ZERO;

    /**
     * The shininess value.
     */
    public int nShininess=0;
    /**
     * Sets the diffuse reflection coefficient of the material using Double3 object.
     *
     * @param kD The diffuse reflection coefficient.
     * @return The Material object with the updated diffuse reflection coefficient.
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }
    /**
     * Sets the diffuse reflection coefficient of the material using a single double value.
     *
     * @param kD The diffuse reflection coefficient.
     * @return The Material object with the updated diffuse reflection coefficient.
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }
    /**
     * Sets the specular reflection coefficient of the material using Double3 object.
     *
     * @param kS The specular reflection coefficient.
     * @return The Material object with the updated specular reflection coefficient.
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }
    /**
     * Sets The specular reflection coefficient of the material using a single value.
     *
     * @param kS The specular reflection coefficient.
     * @return The Material object with the updated specular reflection coefficient.
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }
    /**
     * Sets The shininess value of the material.
     *
     * @param nShininess The shininess value.
     * @return The Material object with the updated shininess value.
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}