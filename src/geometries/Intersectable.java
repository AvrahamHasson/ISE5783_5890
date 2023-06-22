package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * The abstract class Intersectable is a class that presents a meeting between rays and geometric
 * bodies in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */

public abstract class Intersectable {
    /**
     * An inner class that represents a geometric body and a point that belongs to it.
     */
    public static class GeoPoint {
        /**
         * Geometric body
         */
        public Geometry geometry;
        /**
         * A point that belongs to the geometric body
         */
        public Point point;

        /**
         *A constructor for a class that receives a geometric body and a point and on it.
         * @param geometry  The geometric body.
         * @param point The point and on it.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint: " +
                    "geometry= " + geometry +
                    ", point= " + point;
        }
    }

    /**
     * Returns the collection of intersection points between the ray and the geometry.
     *
     * @param ray The ray that creates the intersection points.
     * @return The points of intersection of the geometry with the ray.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Returns the collection of intersection points between the ray and the geometry
     * So that for each intersection point we know what body it cuts..
     *
     * @param ray The ray that creates the intersection points.
     * @return The points of intersection of the geometry with the ray.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray){
        return this.findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper function for the " findGeoIntersections" function.
     *
     * @param ray The ray that creates the intersection points.
     * @return The points of intersection of the geometry with the ray.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}