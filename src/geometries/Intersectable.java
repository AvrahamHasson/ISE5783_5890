package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The interface Intersectable is an interface that presents a meeting between rays and geometric
 * bodies in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */

public interface Intersectable {

    /**
     * Returns the collection of intersection points between the ray and the geometry.
     *
     * @param ray The ray that creates the intersection points.
     * @return The points of intersection of the geometry with the ray.
     */
    List<Point> findIntersections(Ray ray);
}