import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Light implements Runnable{
    public static boolean horizontalRed = true, verticalRed = false;
    private Point location;
    private String orientation;
    private boolean isRed;
    private BufferedImage image, redLight, greenLight;
    private RoadPanel panel;

    public Light(RoadPanel panel, Point location, String orientation, boolean isRed) {
       this.panel = panel; 
       this.location = location;  
       this.orientation = orientation;
       this.isRed = isRed;
       loadImage();
       switchLights();
    }

    public void loadImage() {
        try {
            redLight = ImageIO.read(new File(RoadPanel.path + orientation + "RedLight.png"));
            greenLight = ImageIO.read(new File(RoadPanel.path + orientation + "GreenLight.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            switchLights();
            try {
                Thread.sleep(7000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            panel.repaint();
            if (location.x == 340 && location.y == 130)
                changeHorizontalAndVertical();
        }
    }

    public  void changeHorizontalAndVertical() {
       horizontalRed = !horizontalRed;
       verticalRed = !verticalRed;  
    }

    public void switchLights() {
        if (isRed)
            image = redLight;
        else
            image = greenLight; 
        isRed = !isRed;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Point getLocation() {
        return location;
    }

    public String getOrientation() {
        return orientation;
    }

    public static boolean getHorizontalRed() {
        return horizontalRed;
    }

    public static boolean getVerticalRed() {
        return verticalRed;
    }
}
