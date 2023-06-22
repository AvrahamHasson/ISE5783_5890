package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static java.lang.System.out;
import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class Camera is a class representing a Camera
 * that shoots from a certain place, in a certain direction,
 * in the three-dimensional space
 *
 * @author Avraham Hassson
 */
public class Camera {
    /**
     * The point where the Camera is located.
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
     * Intended for creating the image file
     */
    private ImageWriter imageWriter;
    /**
     * Intended for dyeing the rays.
     */
    private RayTracerBase rayTracerBase;

    /**
     * Constructor to initialize Camera based on the location point of the Camera,
     * a vector that points towards the camera, and
     * a vector that points to the right of the camera.
     *
     * @param p  Camera location.
     * @param vT points towards the camera.
     * @param vU points to the right of the camera.
     */
    public Camera(Point p, Vector vT, Vector vU) {
        if (!isZero(vU.dotProduct(vT)))
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
    @SuppressWarnings("unused")
    public Vector getVUp() {
        return vUp;
    }

    /**
     * getter for Camera direction.
     *
     * @return Camera direction.
     */
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
    public double getViewPlaneD() {
        return viewPlaneD;
    }

    /**
     * setter for view plane width and height.
     *
     * @param width  view plane width.
     * @param height view plane height.
     * @return the Camera.
     */
    public Camera setVPSize(double width, double height) {
        if (alignZero(width) <= 0 || alignZero(height) <= 0)
            throw new IllegalArgumentException("The height and width must be greater than zero");
        this.viewPlaneH = height;
        this.viewPlaneW = width;
        return this;
    }

    /**
     * setter for view plane distance.
     *
     * @param distance view plane distance.
     * @return the Camera.
     */
    public Camera setVPDistance(double distance) {
        if (alignZero(distance) <= 0)
            throw new IllegalArgumentException("The distance must be greater than zero");
        this.viewPlaneD = distance;
        return this;
    }

    /**
     * setter for image writer.
     *
     * @param imageWriter image writer.
     * @return the Camera.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Setter for ray tracer base.
     *
     * @param rayTracerBase Ray tracer base.
     * @return The Camera.
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * receives a specific slot, with a selected resolution of the view plane,
     * and returns the ray coming out of the Camera to the view plane.
     *
     * @param nX The number of pixels in a row in the view plane.
     * @param nY The number of pixels in a column in the view plane.
     * @param j  The row number of the pixel.
     * @param i  The column number of the pixel.
     * @return ray coming out of the Camera to the view plane.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pc = this.p0.add(this.vTo.scale(viewPlaneD));
        double rY = viewPlaneH / (double) nY;
        double rX = viewPlaneW / (double) nX;
        double yI = -(i - (((double) nY - 1) / 2)) * rY;
        double xJ = (j - ((double) nX - 1) / 2) * rX;

        Point pIJ = pc;
        if (xJ != 0)
            pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0)
            pIJ = pIJ.add(vUp.scale(yI));

        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }

    /**
     * Calculates a color for a specific pixel in an image.
     *
     * @param nX The number of pixels in a row in the view plane.
     * @param nY The number of pixels in a column in the view plane.
     * @param j  The row number of the pixel.
     * @param i  The column number of the pixel.
     */
    private void castRay(int nX, int nY, int j, int i) {
        this.imageWriter.writePixel(j, i, this.rayTracerBase.traceRay(this.constructRay(nX, nY, j, i)));
    }

    /**
     * Renders the image by casting rays from the camera through each pixel of the image and writing the resulting color to the imageWriter.
     * Throws UnsupportedOperationException if any of the required resources are missing (rayTracerBase, imageWriter, width, height, distance).
     */
    public Camera renderImage() {
        if (this.rayTracerBase == null || this.imageWriter == null || this.viewPlaneW == 0 || this.viewPlaneH == 0 || this.viewPlaneD == 0)
            throw new UnsupportedOperationException("MissingResourcesException");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++)
            for (int j = 0; j < nX; j++)
                this.castRay(nX, nY, j, i);
        return this;
    }

    /**
     * Draws a grid on the image by writing a specified color to the pixels that fall on the grid lines.
     * Throws UnsupportedOperationException if imageWriter object is null.
     *
     * @param interval The spacing between grid lines.
     * @param color    The color to use for the grid lines.
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null) throw new UnsupportedOperationException("MissingResourcesException");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i+=interval)
            for (int j = 0; j < nX; j++)
                this.imageWriter.writePixel(j, i, color);
        for (int i = 0; i < nX; i+=interval)
            for (int j = 0; j < nX; j++)
                this.imageWriter.writePixel(i, j, color);
    }

    /**
     * Writes the rendered image to the output file using the imageWriter object.
     * Throws UnsupportedOperationException if imageWriter object is null.
     */
    public void writeToImage() {
        if (imageWriter == null) throw new UnsupportedOperationException("MissingResourcesException");
        this.imageWriter.writeToImage();
    }
}
