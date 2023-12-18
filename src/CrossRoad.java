import java.util.ArrayList;
import java.awt.Point;
public class CrossRoad {
    private static final int[][] range = { {377,178,359,160}, {359,395,377,413}, {82,395,100,413}, {82,160,100,178}};
    private static final String[][] orientation = {{"north","west","south","east"}, {"north","west","east"}, {"north","south","east"}, {"south","west","east","north"}};
    private static final Point[][] locationPoints = {{new Point(340,130), new Point(320,200), new Point(400,190), new Point(390, 140)}, 
                                              {new Point(340, 365), new Point(340,440), new Point(390,375)},
                                              {new Point(60, 375), new Point(125, 425), new Point(110, 380)},
                                              {new Point(125, 190), new Point(50, 200), new Point(115, 140), new Point(65, 130)}};
    private static final boolean[][] redSetUp = {{true, false, true, false}, {true, false, false}, {true, true, false}, {true, false, false, true}};
    private RoadPanel panel;
    private int minX, maxX, minY, maxY;
    private ArrayList<Light> lights = new ArrayList<>();

    public CrossRoad(RoadPanel targetPanel, int index) {
        panel = targetPanel;
        loadImages(index);
        startLightThreads();
        loadRanges(index);
    }

    public boolean loadImages(int index) {
     switch (index) {
        case 0: 
            return initializeLights(index); 
        case 1: 
            return initializeLights(index);    
        case 2: 
            return initializeLights(index);
        case 3: 
            return initializeLights(index); 
        default:
            return false;
      } 
    }

    public boolean initializeLights(int index) {
        for (int i = 0; i < length(index); i++)
            lights.add(new Light(panel, locationPoints[index][i], orientation[index][i], redSetUp[index][i]));
        return true;
    }

    public int length(int index) {
        if (index == 0 || index == 3)
            return 4;
        else
            return 3;
    }

    public void loadRanges(int index) {
        minX = range[index][0]; 
        minY = range[index][1];
        maxX = range[index][2];
        maxY = range[index][3];
    }

    public void startLightThreads() {
        for (Light light : lights) 
            new Thread(light).start();
    }

    public int getNumberOfExits() {
        return lights.size();
    }

    public boolean checkIfPossibleTurn(String orientation) {
       for (Light light : lights)
        if (light.getOrientation().equals(orientation))
            return true;
        return false;
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public Light getLight(String orientationOfLight) {
       for (Light light : lights) 
        if (light.getOrientation().equals(orientationOfLight))
            return light;
       return null;
    }

    public void setLights(ArrayList<Light> lights) {
        this.lights = lights;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }
    
}