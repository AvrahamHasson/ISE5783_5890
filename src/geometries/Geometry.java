package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface Geometry is an interface representing a geometry
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public interface Geometry extends Intersectable {
    /**
     * Returns the normal vector to the surface of the geometry at a given point
     *
     * @param point the given point for which we return the normal vector
     * @return the normal vector at the given point
     */
    public Vector getNormal(Point point);
}
