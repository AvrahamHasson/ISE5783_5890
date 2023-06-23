package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class Plane is a class representing a plane
 * of Euclidean geometry in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Plane extends Geometry {
    /**
     * point in plane
     */
     final private Point q0;
    /**
     * normal vector to the plane
     */
     final private Vector normal;

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
     * Computes the intersection point(s) between the current plane and a given ray.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        //if the direction is orthogonal to the normal so the ray is parallel to the plane or included
        if(normal.dotProduct(ray.getDir()) == 0)
            return null;
        //if the ray begins at the reference point of the plane it will create a vector zero
        try {
            double t = normal.dotProduct(q0.subtract(ray.getP0())) / normal.dotProduct(ray.getDir());
            //if the ray begins at the plane or after the distance t<=0
            if (t <= 0)
                return null;
            return List.of(new GeoPoint(this,ray.getPoint(t)));
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
