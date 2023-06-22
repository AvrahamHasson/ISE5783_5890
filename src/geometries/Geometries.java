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
public class Geometries extends Intersectable {
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> lp = null;
        for (Intersectable intersectable : geometries) {
            var i = intersectable.findGeoIntersectionsHelper(ray);
            if (i != null) {
                if (lp == null) lp = new LinkedList<GeoPoint>();
                lp.addAll(i);
            }
        }
        return lp;
    }
}