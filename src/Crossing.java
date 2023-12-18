public class Crossing {
    private static int range[][] = {{166,141,238,199},{62,241,121,311}, {167,375,238,434}, {339,242,398,312}};
    private int index, minX, minY, maxX, maxY;
    private boolean isPedestrainOn = false;

    public Crossing(int i) {
        index = i;
        setCrossingRanges(index);
    }

    private void setCrossingRanges(int index) {
        minX = range[index][0];    
        minY = range[index][1];
        maxX = range[index][2];
        maxY = range[index][3];
    }

    public boolean checkInRange(RoadUser user) {
        return user.getUserPoint().getX() >= minX && user.getUserPoint().getX() <= maxX && user.getUserPoint().getY() >= minY && user.getUserPoint().getY() <= maxY;
    }

    public boolean isPedestrainOn() {
        return isPedestrainOn;
    }

    public void setPedestrainOnCrossing(boolean state) {
        isPedestrainOn = state;
    }


    public int getIndex() {
        return index;
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
