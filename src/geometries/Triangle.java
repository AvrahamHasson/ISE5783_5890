package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * class Triangle is a basic class representing a triangle
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Triangle extends Polygon {
    /**
     * Constructor to initialize Triangle based on a normal vector and 3 points of the plane
     *
     * @param p1 first point of the triangle
     * @param p2 second point of the triangle
     * @param p3 third point of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    /**
     * Computes the intersection point(s) between the current triangle and a given ray.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        var intersection = plane.findGeoIntersectionsHelper(ray);
        if (intersection == null) //at first find if thar is intersection with the plane of the triangle
            return null;

        //calculate according to the calculation in the course's book
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        Vector v1 = this.vertices.get(0).subtract(p0);
        Vector v2 = this.vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double s1 = alignZero(dir.dotProduct(n1));
        if (s1 == 0) return null;

        Vector v3 = this.vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double s2 = alignZero(dir.dotProduct(n2));
        if (s1 * s2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double s3 = alignZero(dir.dotProduct(n3));
        if (s1 * s3 <= 0) return null;
        intersection = List.of(new GeoPoint(this,intersection.get(0).point));
        return intersection;
    }
}
