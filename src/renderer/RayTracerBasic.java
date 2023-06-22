package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

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
        GeoPoint geoPoint = ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (geoPoint == null)
            return scene.background;
        return this.calcColor(geoPoint, ray);
    }

    /**
     * Calculates the diffuse component of the material's reflection at the given intersection point.
     *
     * @param material The material properties
     * @param nl The dot product of the surface normal and the light direction
     * @return The calculated diffuse component as a Double3 object
     */
    private Double3 calcDiffusive(Material material, double nl) {

        return (material.kD).scale(Math.abs(nl));
    }
    /**
     * Calculates the specular component of the material's reflection at the given intersection point.
     *
     * @param material The material properties
     * @param n The surface normal vector
     * @param l The light direction vector
     * @param nl The dot product of the surface normal and the light direction
     * @param v The view direction vector
     * @return The calculated specular component as a Double3 object
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        // the reflectance vector
        Vector r = l.subtract(n.scale(2 * nl));
        double minusVr = -v.dotProduct(r);
        return material.kS.scale((minusVr>0)? Math.pow(minusVr,material.nShininess) : 0);
    }


    /**
     * Calculates the local effects of lighting on a given geometric point.
     *
     * @param gp The geometric point to calculate the local effects for
     * @param ray The ray used for the calculation
     * @return The resulting color after considering local lighting effects
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }


    /**
     * Computes the color of the intersection point using the Phong reflection model.
     *
     * @param geoPoint the intersection point
     * @return the color of the intersection point
     */
    private Color calcColor(GeoPoint geoPoint,Ray ray){
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(geoPoint, ray));
    }
}
