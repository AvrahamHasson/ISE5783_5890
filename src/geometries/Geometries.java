package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * class Geometries is a class representing a set of geometric shapes
 * in Cartesian 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Geometries implements Intersectable {
    /**
     * set of geometric shapes
     */
    private List<Intersectable> geometries;
    /**
     * Constructor to initialize Geometries.
     */
    public Geometries() {
        this.geometries = new LinkedList<>();
    }
    /**
     * Constructor to initialize Geometries based on geometric shapes.
     *
     * @param geometries The intersectable geometries to be added to the Geometries object.
     */
    public Geometries(Intersectable... geometries) {
        if (geometries != null) {
            this.geometries = (List.of(geometries));
        }

    }
    /**
     * Adds intersectable geometries to the existing list of geometries.
     *
     * @param geometries The intersectable geometries to be added.
     */
    public void add(Intersectable... geometries) {
        if (geometries != null) {
            this.geometries.addAll(List.of(geometries));
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        boolean intersectExist = false;
        for (Intersectable element : geometries) {
            if (element.findIntersections(ray) != null) {
                intersectExist = true;
                break;
            }
        }
        if (!intersectExist) {
            return null;
        }

        ArrayList<Point> IntersectionsPoints = new ArrayList<>();
        for (Intersectable element : geometries) {
            if (element.findIntersections(ray) != null) {
                IntersectionsPoints.addAll(element.findIntersections(ray));
            }
        }
        return List.of(IntersectionsPoints.toArray(new Point[0]));
    }
}