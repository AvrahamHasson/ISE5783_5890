package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Ray {
    /**
     * starting point of the ray
     */
    private final Point p0;
    /**
     * direction vector of the ray
     */
    private final Vector dir;

    /**
     * Constructor to initialize Ray based on point and a vector
     *
     * @param p0  starting point of the ray
     * @param dir direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * getter for starting point of the ray p0
     *
     * @return p0
     */
    public Point getP0() {
        return p0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }

    /**
     * getter for direction vector of the ray dir
     *
     * @return dir
     */
    public Vector getDir() {
        return dir;
    }

    @Override
    public int hashCode() {
        return p0.hashCode() + dir.hashCode();
    }

    @Override
    public String toString() {
        return "Ray: " + "starting point = " + p0 + ", direction = " + dir;
    }

    /**
     * getter for the value of progress of length t on the starting point
     *
     * @param t The parameter value.
     * @return The point on the line corresponding to the parameter value.
     */
    public Point getPoint(double t) {
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * Finds the closest point to the start of the ray among the given points.
     *
     * @param points The list of points to check.
     * @return The closest point to the start of the ray.
     */
    public Point findClosestPoint(List<Point> points) {
        return (points == null|| points.isEmpty()) ? null : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closest GeoPoint to the start of the ray among the given GeoPoints.
     *
     * @param geoPoints The list of GeoPoints to check.
     * @return The closest geoPoint to the start of the ray.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        if (geoPoints == null)
            return null;

        GeoPoint closestGeoPoint = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (var currentGeoPoint : geoPoints) {
            double currentDistance = currentGeoPoint.point.distance(this.p0);
            if (currentDistance < closestDistance) {
                closestGeoPoint = currentGeoPoint;
                closestDistance = currentDistance;
            }
        }

        return closestGeoPoint;
    }
}