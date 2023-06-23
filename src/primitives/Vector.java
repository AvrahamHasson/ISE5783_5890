package primitives;

/**
 * Class Vector is the basic class representing a vector of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Vector extends Point {
    /**
     * Constructor to initialize Vector based on 3 double numbers (Double3) value
     *
     * @param vec number value for all 3 numbers
     * @throws IllegalArgumentException if xyz = (0,0,0)
     */
    Vector(Double3 vec) {
        super(vec);
        if (vec.equals(Double3.ZERO))
            throw new IllegalArgumentException("cannot create a zero vector");
    }

    /**
     * Constructor to initialize Vector based on 3 number values
     *
     * @param x number value for x coordinate
     * @param y number value for y coordinate
     * @param z number value for z coordinate
     * @throws IllegalArgumentException if xyz = (0,0,0)
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("cannot create a zero vector");
    }

    @SuppressWarnings("unused")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Vector other)
            return super.equals(obj);
        return false;
    }

    @Override
    public String toString() {
        return "‐>" + super.toString();
    }

    /**
     * Sums two vectors into a new vector where each coordinate
     * is summarized
     *
     * @param vector right handle side operand for addition
     * @return vector result of addition
     */
    public Vector add(Vector vector) {
        return new Vector(vector.xyz.add(this.xyz));
    }

    /**
     * scales a vector into a new vector where each coordinate
     * is multiplied by the scale factor
     *
     * @param factor factor for scaling the vector
     * @return result of scale
     */
    public Vector scale(double factor) {
        return new Vector(this.xyz.scale(factor));
    }

    /**
     * calculates the dot product of two vectors
     *
     * @param vector right handle side operand for dot product calculation
     * @return result of dot product
     */
    public double dotProduct(Vector vector) {
        //dot product of two vectors (x1,y1,z1) and (x2,y2,z2)
        //is x1*x2 + y1*y2 + z1*z2
        return this.xyz.d1 * vector.xyz.d1 +
                this.xyz.d2 * vector.xyz.d2 +
                this.xyz.d3 * vector.xyz.d3;
    }

    /**
     * calculates the cross product of two vectors
     *
     * @param vector right handle side operand for cross product calculation
     * @return result of cross product
     */
    public Vector crossProduct(Vector vector) {
        //cross product of two vectors (x1,y1,z1) and (x2,y2,z2)
        //is (y1*z2-z1*y2, z1*x2-x1*z2, x1*y2-y1*x2)
        return new Vector(
                this.xyz.d2 * vector.xyz.d3 - this.xyz.d3 * vector.xyz.d2,
                this.xyz.d3 * vector.xyz.d1 - this.xyz.d1 * vector.xyz.d3,
                this.xyz.d1 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d1);
    }

    /**
     * calculates length of the vector squared
     *
     * @return length of vector squared
     */
    public double lengthSquared() {
        //for any vector v, |V|^2 = v * v [dot product]
        return dotProduct(this);
    }

    /**
     * calculates length of the vector
     *
     * @return length of the vector
     */
    public double length() {
        //for any vector v, |V| = sqrt(v * v) [dot product]
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * normalizes the vector
     *
     * @return a new normalized vector
     */
    public Vector normalize() {
        return new Vector(this.xyz.reduce(this.length()));
    }
}
