import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class App {
    public static final String LOGIN = "LOGIN";
    public static final String MENU = "MENU";
    public static final String GAME = "GAME";
    public static final String GAMEOVER = "GAMEOVER";
    public static final String SIGN = "SIGN";
    public static final String PLAY = "PLAY";
    

    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;
    private GamePanel gamePanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}