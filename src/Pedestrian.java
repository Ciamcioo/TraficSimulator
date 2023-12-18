public class Pedestrian extends RoadUser implements Runnable {
    public static final String pedestrianType = "yellowHead.png", noOrientation = "";
    public static final int PEDESTRIAN_SPEED = 3, WEST_CROSSING = 1, NORTH_CROSSING = 0, SOUTH_CROSSING = 2, EAST_CROSSING = 3;
    public Pedestrian(RoadPanel targetPanel)  {
        super(targetPanel, "Pedestrain");
        setObjectImage(loadImage(noOrientation, pedestrianType));
        new Thread(this).start();
    } 

    @Override
    public void run() {
        boolean wasMoved = true;
        boolean isOnCrossing = false;
        while (wasMoved) {
            wasMoved = moveUser(PEDESTRIAN_SPEED);
            isOnCrossing = checkIfInCrossingRange();
            sleep();            
            getPanel().repaint();
            if (isOnCrossing)
                checkIfLeftCrossing();
        }
        remove();
        return; 
    } 

    public boolean checkIfInCrossingRange() {
        for (Crossing crossing : getPanel().getCrossings())
            if (crossing.checkInRange(this)) { 
               crossing.setPedestrainOnCrossing(true); 
               return true;
            }
        return false;
    }

    public void checkIfLeftCrossing(){
       for (Crossing crossing : getPanel().getCrossings())
        if (!crossing.checkInRange(this) && crossing.isPedestrainOn()) 
            crossing.setPedestrainOnCrossing(false);
    }

}
