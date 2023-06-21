package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;
/**
 * Represents a scene in a computer graphics environment.
 *
 * @author Avraham Hassson
 */
public class Scene {
    /**
     * The name of the scene.
     */
    public String name;
    /**
     * The background color of the scene.
     */
    public Color background;
    /**
     * The ambient light of the scene.
     */
    public AmbientLight ambientLight;
    /**
     * The geometries in the scene.
     */
    public Geometries geometries;
    /**
     * Constructs a new Scene object with the given name.
     *
     * @param name The name of the scene.
     */
    public Scene(String name) {
        this.name = name;
        this.background = Color.BLACK;
        this.ambientLight = AmbientLight.NONE;
        this.geometries = new Geometries();
    }
    /**
     * Sets the background color of the scene.
     *
     * @param background The background color.
     * @return The updated Scene object.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }
    /**
     * Sets the ambient light of the scene.
     *
     * @param ambientLight The ambient light.
     * @return The updated Scene object.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    /**
     * Sets the geometries of the scene.
     *
     * @param geometries The geometries.
     * @return The updated Scene object.
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
