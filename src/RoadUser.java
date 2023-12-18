import java.awt.image.*;
import java.awt.*;
import java.util.Random;
import javax.imageio.ImageIO;
import java.io.*;

public class RoadUser {
   private static Point[] pedestrainArray = {new Point(217, 20), new Point(20,294), new Point(212, 480), new Point(480, 294)},
                           carArray = {new Point(370, 5), new Point(10, 175), new Point(90, 460)};
   private BufferedImage objectImage; 
   private Point currentPoint, direction;
   private RoadPanel panel;
      
   public RoadUser(RoadPanel targetPanel, String type) {
      do {
         if (type.equals("Car"))
            currentPoint = generateStartingPoint(carArray);
         else
            currentPoint = generateStartingPoint(pedestrainArray); 
      } while(currentPoint == null);
      this.panel = targetPanel;
   }

   public Point generateStartingPoint(Point[] setOfStartingPoints) {
      switch(new Random().nextInt(setOfStartingPoints.length)) {
      case 0: 
         return setStartingPoint(setOfStartingPoints, 0, Direction.SOUTH);
      case 1: 
         return setStartingPoint(setOfStartingPoints, 1, Direction.EAST);
      case 2: 
         return setStartingPoint(setOfStartingPoints, 2, Direction.NORHT);
      case 3:
         return setStartingPoint(setOfStartingPoints, 3, Direction.WEST);
      default: 
         return null;    
      } 
   }

   public void sleep() {
      try {
         Thread.sleep(50);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   public void remove() {
      getPanel().getRoadUsers().remove(this);
      getPanel().repaint();
   }

   private Point setStartingPoint(Point[] pointSet, int index, Point nextDirection ) {
      if (pointSet[index] != null) {
         Point setPoint = pointSet[index];
         direction = nextDirection;
         pointSet[index] = null;
         return setPoint;
      }
      else
         return null;
   }

   public boolean moveUser(int multiplayer) {
      if (!checkMapEnd()) {
         currentPoint.x = currentPoint.x + multiplayer * direction.x;
         currentPoint.y = currentPoint.y + multiplayer * direction.y;
         return true; 
      }
      else 
         return false;
   }

   public boolean checkMapEnd() {
     if (currentPoint.x + direction.x >= 490 || currentPoint.x + direction.x <= 1)  
         return true;
      else if (currentPoint.y + direction.y >= 490 || currentPoint.y + direction.y <= 1)
         return true;
      else 
         return false;
   }

   public String directioString() {
      if (getDirectionPoint().getX() == 0)
         if (getDirectionPoint().getY() == 1)
            return "south";
         else
            return "north";
      else if (getDirectionPoint().getX() == 1)
         return "east";
      else
         return "west";
   }

   public BufferedImage loadImage(String orientation, String typeOfUser) {
      try {
         return ImageIO.read(new File(RoadPanel.path + orientation + typeOfUser));
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

   public BufferedImage getObjectImage() {
      return objectImage;
   }

   public void setObjectImage(BufferedImage image) {
      objectImage = image;
   }

   public Point getUserPoint() {
      return currentPoint;
   }

   public void setUserPoint(Point newPoint) {
      currentPoint = new Point();
   }

   public Point getDirectionPoint() {
      return direction;
   }

   public void setDirectionPoint(Point newDirection) {
      this.direction = newDirection;
   }

   public RoadPanel getPanel() {
      return panel;
   }
}