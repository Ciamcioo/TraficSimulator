import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.io.*;

public class RoadPanel extends JPanel {
    public static String path = "E:\\workspace\\ElementsOfTraficSimulation\\";
    private JFrame frame = new JFrame();
    private BufferedImage map; 
    private ArrayList<CrossRoad> crossRoads = new ArrayList<>();
    private ArrayList<RoadUser> users = new ArrayList<>();
    private ArrayList<Crossing> crossings = new ArrayList<>();

    public RoadPanel() {
        frameInitalization();
        loadMap();
        loadCrossRoads();
        loadUsers();
        loadCrossing();
        frame.add(this);
        frame.setVisible(true);
    }

    private void frameInitalization() {
        frame.setTitle("Trafic Simulation");
        frame.setSize(new Dimension(514, 535));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.white);
    }

    public void loadMap()  {
        try {
            map = ImageIO.read(new File(path + "map.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        for (int i = 0; i < 2; i++) {
            users.add(new Car(this));
            users.add(new Pedestrian(this));
        }
    }

    public void loadCrossRoads() {
        for (int i = 0; i < 4; i++)
            crossRoads.add(new CrossRoad(this,i));
    }

    public void loadCrossing() {
        for (int i = 0; i < 4; i++)
            crossings.add(new Crossing(i));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(map, 0, 0,  this);
        for (CrossRoad crossRoad : crossRoads) 
            for (Light light : crossRoad.getLights())
                g.drawImage(light.getImage(), (int) light.getLocation().getX(), (int) light.getLocation().getY(), this);
        for (RoadUser user : users) {
            g.drawImage(user.getObjectImage(),  (int) user.getUserPoint().getX(), (int) user.getUserPoint().getY(), this);
        }
    }

    public ArrayList<CrossRoad> getCrossRoads() {
        return crossRoads;
    }

    public ArrayList<RoadUser> getRoadUsers() {
        return users;
    }

    public ArrayList<Crossing> getCrossings() {
        return crossings;
    }
}