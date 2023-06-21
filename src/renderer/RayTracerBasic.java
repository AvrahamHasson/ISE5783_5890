package renderer;

import primitives.*;
import scene.Scene;

/**
 * A basic implementation of the {@code RayTracerBase} class that computes the color of the closest intersection point with the scene.
 *
 * @author Avraham Hassson
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * Constructor that takes a scene as a parameter and invokes the base class constructor.
     *
     * @param scene The scene for rendering.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray and returns the color.
     *
     * @param ray The ray to trace.
     * @return The color of the traced ray.
     */
    @Override
    public Color traceRay(Ray ray) {
        Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray));
        if (point == null)
            return scene.background;
        return this.calcColor(point);
    }


    /**
     * Computes the color of the intersection point using the Phong reflection model.
     *
     * @param point the intersection point
     * @return the color of the intersection point
     */
    private Color calcColor(Point point) {
        return this.scene.ambientLight.getIntensity();
    }
}
