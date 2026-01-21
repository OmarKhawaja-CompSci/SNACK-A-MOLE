import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public abstract class BaseScreen extends JPanel {
    protected MainFrame mainFrame;
    protected ScoreManager scoreManager;

    public BaseScreen(MainFrame mainFrame, ScoreManager scoreManager) {
        this.mainFrame = mainFrame;
        this.scoreManager = scoreManager;
    }

    protected ImageIcon loadSprite(String path, int width, int height) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                BufferedImage img = ImageIO.read(imgURL);
                Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaled);
            }
        } catch (Exception e) {
  
        }
        return null;
    }
}