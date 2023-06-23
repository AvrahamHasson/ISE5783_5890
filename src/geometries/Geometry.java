package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * Abstract class Geometry is a class representing a geometry
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public  abstract class Geometry extends Intersectable {
    /**
     * Represents the Geometry color.
     */
    protected Color emission=Color.BLACK;

    /**
     * The material of the geometry. Initialized with a default Material object.
     */
    private Material material = new Material();

    /**
     * getter for the emission.
     * @return the emission.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Setter for the emission.
     * @param emission The emission light.
     * @return The Geometry.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the material of the geometry.
     *
     * @return The material of the geometry.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The new material for the geometry.
     * @return The updated geometry itself.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * Returns the normal vector to the surface of the geometry at a given point
     *
     * @param point the given point for which we return the normal vector
     * @return the normal vector at the given point
     */
    public abstract Vector getNormal(Point point);
}
