package primitives;

import lighting.AmbientLight;

/**
 * Class Point is the basic class representing a point of Euclidean geometry in Cartesian
 * 3-Dimensional coordinate system.
 *
 * @author Avraham Hassson
 */
public class Point {
    /**
     * Represents a constant representing the zero point.
     */
    public static final Point ZERO = new Point(0,0,0);
    /**
     * 3-dimensional coordinates
     */
    final Double3 xyz;

    /**
     * Constructor to initialize Point based object with one 3 double numbers (Double3) value
     *
     * @param po 3 double numbers (Double3) value
     */
    Point(Double3 po) {
        this.xyz = po;
    }

    /**
     * Constructor to initialize Point based object with 3 number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * getter for the first coordinate.
     *
     * @return the first coordinate.
     */
    public double getX() {
        return xyz.d1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Point other)
            return xyz.equals(other.xyz);
        return false;
    }

    @Override
    public int hashCode() {
        return xyz.hashCode();
    }

    @Override
    public String toString() {
        return "" + xyz;
    }

    /**
     * subtracts two points into a new vector from the second
     * point (right handle side) to the first one
     *
     * @param point right handle side operand for subtraction
     * @return vector from first to second (right hand side) point
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Sums a point and a vector into a new point where each coordinate
     * is summarized
     *
     * @param vector right handle side operand for addition
     * @return point result from addition of vector and point
     */
    public Point add(Vector vector) {
        return new Point(vector.xyz.add(this.xyz));
    }

    /**
     * calculates the distance between two points - squared
     *
     * @param point right handle side operand for distance squared calculation
     * @return distance between points - squared
     */
    public double distanceSquared(Point point) {
        //distance between points (x1,y1,z1), (x2,y2,z2) squared is
        // (x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2
        double dx = this.xyz.d1 - point.xyz.d1;
        double dy = this.xyz.d2 - point.xyz.d2;
        double dz = this.xyz.d3 - point.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * calculates the distance between two points
     *
     * @param point right handle side operand for distance calculation
     * @return distance between points
     */
    public double distance(Point point) {
        //distance between points (x1,y1,z1), (x2,y2,z2) is
        // sqrt((x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2)
        return Math.sqrt(this.distanceSquared(point));
    }
}
