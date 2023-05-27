package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    Color intensity;
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);
    AmbientLight(Color iA, Double3 kA){intensity=iA.scale(kA);}
    Color getIntensity(){return this.intensity;}

}
