package geometries;

import primitives.Point;
import primitives.Ray;

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
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Constructor to initialize Geometries.
     */
    public Geometries() {
    }

    /**
     * Constructor to initialize Geometries based on geometric shapes.
     *
     * @param geometries The intersectable geometries to be added to the Geometries object.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
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
        List<Point> intersectionsPoints = null;
        for (Intersectable element : geometries) {
            var elemIntersections = element.findIntersections(ray);
            if (elemIntersections != null) {
                if (intersectionsPoints == null)
                    intersectionsPoints = new LinkedList<>(elemIntersections);
                else
                    intersectionsPoints.addAll(elemIntersections);
            }
        }
        return intersectionsPoints;
    }
}