package renderer;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

/**
 * class Camera is a class representing a Camera
 * that shoots from a certain place, in a certain direction,
 * in the three-dimensional space
 *
 * @author Avraham Hassson
 */
public class Camera {
    /**
     *The point where the Camera is located.
     */
    Point p0;
    /**
     * Vector to the right of the Camera, up, and where it was pointing.
     */
    Vector vUp, vTo, vRight;
    /**
     * The height of the view plane, the width of the view plane, and its distance from the camera.
     */
    double viewPlaneH, viewPlaneW, viewPlaneD;
    /**
     * Constructor to initialize Camera based on the location point of the Camera,
     * a vector that points towards the camera, and
     * a vector that points to the right of the camera.
     *
     * @param p Camera location.
     * @param vT points towards the camera.
     * @param vU points to the right of the camera.
     */
    public Camera(Point p, Vector vT, Vector vU) {
        if (0 != vU.dotProduct(vT))
            throw new IllegalArgumentException("The vectors must be perpendicular to each other");
        this.p0 = p;
        this.vUp = vU.normalize();
        this.vTo = vT.normalize();
        this.vRight = vT.crossProduct(vU).normalize();
    }
    /**
     * getter for Camera location.
     *
     * @return Camera location.
     */
    public Point getP0() {
        return p0;
    }
    /**
     * getter for above the Camera.
     *
     * @return above the Camera.
     */
    public Vector getVUp() {
        return vUp;
    }
    /**
     * getter for Camera direction.
     *
     * @return Camera direction.
     */
    public Vector getVTo() {
        return vTo;
    }
    /**
     * getter for view plane height.
     *
     * @return view plane height.
     */
    public double getViewPlaneH() {
        return viewPlaneH;
    }
    /**
     * getter for view plane width.
     *
     * @return view plane width.
     */
    public double getViewPlaneW() {
        return viewPlaneW;
    }
    /**
     * getter for view plane distance.
     *
     * @return view plane distance.
     */
    public double getViewPlaneD() {
        return viewPlaneD;
    }

    /**
     * setter for view plane width and height.
     *
     * @param width view plane width.
     * @param height view plane height.
     *
     * @return the Camera.
     */
    public Camera setVPSize(double width, double height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("The height and width must be greater than zero");
        this.viewPlaneH = height;
        this.viewPlaneW = width;
        return this;
    }

    /**
     * setter for view plane distance.
     *
     * @param distance view plane distance.
     *
     * @return the Camera.
     */
    public Camera setVPDistance(double distance) {
        if (distance <= 0)
            throw new IllegalArgumentException("The distance must be greater than zero");
        this.viewPlaneD = distance;
        return this;
    }

    /**
     * receives a specific slot, with a selected resolution of the view plane,
     * and returns the ray coming out of the Camera to the view plane.
     * @param nX The number of pixels in a row in the view plane.
     * @param nY The number of pixels in a column in the view plane.
     * @param j The row number of the pixel.
     * @param i The column number of the pixel.
     *
     * @return ray coming out of the Camera to the view plane.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = this.p0.add(this.vTo.scale(viewPlaneD));
        double rY = viewPlaneH / (double)nY;
        double rX = viewPlaneW / (double)nX;
        double yI = -1 * (i - (((double)nY - 1) / 2)) * rY;
        double xJ = (j - ((double)nX - 1) / 2) * rX;
        Point pIJ = pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI));
        Vector vIJ = pIJ.subtract(p0);
        Ray ray = new Ray(p0, vIJ);
        return ray;
    }
}
