package primitives;

/**
 * Class Ray is the basic class representing a ray of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Ray {
    /**
     * starting point of the ray
     */
    final Point p0;
    /**
     * direction vector of the ray
     */
    final Vector dir;

    /**
     * getter for starting point of the ray p0
     *
     * @return p0
     */
    public Point getP0() {
        return p0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ray ray = (Ray) o;

        if (!p0.equals(ray.p0)) return false;
        return dir.equals(ray.dir);
    }

    /**
     * getter for direction vector of the ray dir
     *
     * @return dir
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * Constructor to initialize Ray based on point and a vector
     *
     * @param p0  starting point of the ray
     * @param dir direction vector of the ray
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Ray: " + "starting point = " + p0.toString() + ", direction = " + dir.toString();
    }

    public Point getPoint(double t) {
        if(t==0){ return p0;}
        return p0.add(dir.scale(t));
    }
}
