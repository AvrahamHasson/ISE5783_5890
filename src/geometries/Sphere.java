package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.List;

/**
 * class Sphere is a class representing a sphere
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Sphere extends RadialGeometry {
    /**
     * center point of the sphere
     */
    final private Point center;

    /**
     * Constructor to initialize Sphere based on a center point and a radius of the sphere
     *
     * @param center center of the sphere
     * @param radius radius of the sphere
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * getter for the center point of the sphere
     *
     * @return center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(this.center).normalize();
    }

    /**
     * Computes the intersection point(s) between the current sphere and a given ray.
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        if (center.equals(p0)) //ray stars at the center
            return List.of(ray.getPoint(radius));

        Vector u = this.center.subtract(p0);
        double tm = dir.dotProduct(u);
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = alignZero(radiusSquared - dSquared);
        if (thSquared <= 0) { //ray does not intersect or tangent
            return null;
        }
        double th = Math.sqrt(thSquared);
        double t2 = alignZero(tm + th);
        if (t2 <= 0)
            return null;

        double t1 = alignZero(tm - th);
        return t1 <= 0 ? List.of(ray.getPoint(t2)) : List.of(ray.getPoint(t1), ray.getPoint(t2));
    }
}
