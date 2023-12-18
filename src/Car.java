import java.util.*;
import java.awt.Point;

public class Car extends RoadUser implements  Runnable{
   public static final int CAR_SPEED = 5, WEST_NORTH_LIGHT = 40, EAST_SOUTH_LIGHT = 20, CROSS_ROAD = 0;
   public static final String carType = "BlueCar.png";
   private boolean enteredCrossRoad = false;

   public Car(RoadPanel targetPanel){
      super(targetPanel, "Car");
      setObjectImage(loadImage(directioString(), carType));
      new Thread(this).start();
   }

   @Override
   public void run() {
      boolean wasMoved = true;
      while(wasMoved) { 
         if (chekcLights() && checkCrossings())
            wasMoved = moveUser(CAR_SPEED);
         if (checkIfInCrossRoad(CROSS_ROAD) != null && !enteredCrossRoad) {
            enteredCrossRoad = true;
            setDirectionPoint(generateDirection(checkIfInCrossRoad(CROSS_ROAD)));
            setObjectImage(loadImage(directioString(), carType));
         }
         else if (checkIfInCrossRoad(CROSS_ROAD) == null && enteredCrossRoad)
            enteredCrossRoad = false;
         sleep();
         getPanel().repaint();
      } 
      remove();
      return;
   }

   public boolean chekcLights() {
      String direction = directioString();
      if (direction.equals("east") || direction.equals("west"))
         return !Light.getHorizontalRed(); 
      else 
         return !Light.getVerticalRed();
   }

   public CrossRoad checkIfInCrossRoad(int shift) {
      ArrayList<CrossRoad> crossRoads = getPanel().getCrossRoads();
      for (CrossRoad crossRoad : crossRoads)
         if (checkRange(crossRoad)) 
            return crossRoad;
      return null;  
   }

   public boolean checkCrossings() {
      for (Crossing crossing : getPanel().getCrossings())
         if (crossing.checkInRange(this) && crossing.isPedestrainOn())
            return false;
      return true;
   }

   public boolean checkRange(CrossRoad crossRoad) {
      return crossRoad.getMinX() <= this.getUserPoint().getX() && this.getUserPoint().getX() <= crossRoad.getMaxX() && crossRoad.getMinY() <= this.getUserPoint().getY() && this.getUserPoint().getY() <= crossRoad.getMaxY(); 
   }

   public Point generateDirection(CrossRoad crossRoad) {
     while(true) { 
         switch (new Random().nextInt(crossRoad.getNumberOfExits())) {
             case 0: { 
               if (crossRoad.checkIfPossibleTurn("north"))
                  return Direction.NORHT; 
               break;
            }
            case 1: { 
               if (crossRoad.checkIfPossibleTurn("east"))
                  return Direction.EAST;
               break;
            }
            case 2: {
               if (crossRoad.checkIfPossibleTurn("south"))
                  return Direction.SOUTH;
               break;
            }
            case 3: {
               if (crossRoad.checkIfPossibleTurn("west"))
                  return Direction.WEST; 
            }
         }
      }
   }
}