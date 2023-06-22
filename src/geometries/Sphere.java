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
    @SuppressWarnings("unused")
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;
        //if the vector from P0 to the center is the zero vector, return one intersection.
        try {
            u = center.subtract(p0);
        } catch (IllegalArgumentException e) {
            //Move radius units in the ray direction:
            return List.of(new GeoPoint(this,ray.getPoint(radius)));
        }
        //tm=the projection of u in the direction of v
        double tm = v.dotProduct(u);
        double dSquared = isZero(tm) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(radius * radius - dSquared);
        //if r<d <=> thSquared<=0, this means that the ray is outside the sphere.
        if (thSquared <= 0) {
            return null;
        }
        double th = Math.sqrt(thSquared);
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        //if: t1>=0 && t2>=0   <=>   the case when the ray starts before the sphere and intersects it twice.
        if (t1 > 0 && t2 > 0)
            return List.of(new GeoPoint(this,ray.getPoint(t1)), new GeoPoint(this,ray.getPoint(t2)));
        //if t2>0 and t1<=0 then return only the point that obtained from t2.
        if (t2 > 0) {
            return List.of(new GeoPoint(this,ray.getPoint(t2)));
        }
        return null;
        //the case when t2<=0 and t1>0 cannot happen.
    }
}
