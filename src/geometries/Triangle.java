package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class Triangle is a basic class representing a triangle
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 * @author Avraham Hassson
 */
public class Triangle extends Polygon {
    /** Constructor to initialize Triangle based on a normal vector and 3 points of the plane
     * @param p1 first point of the triangle
     * @param p2 second point of the triangle
     * @param p3 third point of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3){
        super(p1, p2, p3);
    }

    /**
     Computes the intersection point(s) between the current triangle and a given ray.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        if (super.plane.findIntersections(ray) == null) {//at first find if thar is intersection with the plane of the triangle
            return null;
        }
        //calculate according to the calculation in the course's book
        Point p = super.plane.findIntersections(ray).get(0);
        Vector v1 = this.vertices.get(0).subtract(ray.getP0());
        Vector v2 = this.vertices.get(1).subtract(ray.getP0());
        Vector v3 = this.vertices.get(2).subtract(ray.getP0());
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();
        double a = ray.getDir().dotProduct(n1);
        double b = ray.getDir().dotProduct(n2);
        double c = ray.getDir().dotProduct(n3);
        if (a == 0 || b == 0 || c == 0) {
            return null;
        }
        if (a * b > 0 && b *c > 0) {
            return List.of(p);
        }
        return null;
    }
}
