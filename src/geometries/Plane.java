package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

/**
 * class Plane is a class representing a plane
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Plane implements Geometry {
    /**
     * point in plane
     */
    Point q0;
    /**
     * normal vector to the plane
     */
    Vector normal;

    /**
     * getter to point in plane q0
     *
     * @return q0
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * getter to normal vector to plane normal
     *
     * @return normal
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Constructor to initialize Plane based on a normal vector and point in plane
     *
     * @param q0     point in plane
     * @param normal normal vector to plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructor to initialize Plane based on three points in plane
     *
     * @param p0 first point in plane
     * @param p1 second point in plane
     * @param p2 third point in plane
     * @throws IllegalArgumentException if two or three of the given points are the same point
     */
    public Plane(Point p0, Point p1, Point p2) {
        //normal vector is calculated by the cross product of two vectors
        //representing two edges of the plane
        this.normal = p1.subtract(p0).crossProduct(p1.subtract(p2)).normalize();
        this.q0 = p0;

    }

    /**
     * Computes the intersection point(s) between the current plane and a given ray.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if (ray == null) {//ray cannot be null
            throw new IllegalArgumentException("Ray cannot be null");
        }
        if (ray.getP0().equals(this.q0)) {//start in the plane
            return null;
        }
        //calculate according to the calculation in the course's book
        Vector rayToNormal = this.q0.subtract(ray.getP0());
        double numerator = this.normal.dotProduct(rayToNormal);
        double denominator = this.normal.dotProduct(ray.getDir());
        if (isZero(denominator)) {
            return null;
        }
        double t = alignZero(numerator / denominator);
        if (t > 0) {
            return List.of(ray.getPoint(t));
        }
        return null;
    }
}
