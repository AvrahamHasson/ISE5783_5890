package geometries;

import primitives.Ray;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * class radialGeometry is a class representing a geometry
 * with a radius of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * radius of the geometry
     */
    final protected double radius;
    /**
     * squared radius of the geometry
     */
    final protected double radiusSquared;


    /**
     * Constructor to initialize radialGeometry based on a radius
     *
     * @param radius radius of the geometry
     * @throws IllegalArgumentException if radius &lt;= 0
     */
    public RadialGeometry(double radius) {
        if (alignZero(radius) <= 0)
            throw new IllegalArgumentException("radius cannot be less than or equal to zero");
        this.radius = radius;
        this.radiusSquared = radius * radius;
    }

    /**
     * getter for radius of the geometry
     *
     * @return radius of the geometry
     */
    @SuppressWarnings("unused")
    public double getRadius() {
        return radius;
    }

}
