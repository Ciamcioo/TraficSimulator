public class Application {
    public Application() {
        new RoadPanel();
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() ->  {
            new Application();
        });
    }
}
