import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ScoreManager scoreManager;
    
    public static final String LOGIN = "LOGIN";
    public static final String MENU = "MENU";
    public static final String GAME = "GAME";
    public static final String GAMEOVER = "GAMEOVER";
    public static final String SIGN = "SIGN";
    public static final String PLAY = "PLAY";
    
    
    
  
    private Difficulty currentDifficulty = Difficulty.MEDIUM;

    public MainFrame() {
    setTitle("SNACK-A-MOLE");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setBackground(new Color(60, 179, 113)); 

    scoreManager = new ScoreManager();
    cardLayout = new CardLayout();
    mainPanel = new JPanel(cardLayout);

    MenuPanel menuPanel = new MenuPanel(this, scoreManager);
    GamePanel gamePanel = new GamePanel(this, scoreManager);


    CursorSelectionPanel skinPanel = new CursorSelectionPanel(cardLayout, mainPanel);


    mainPanel.add(new MainPanel(cardLayout, mainPanel), MENU); 
    mainPanel.add(new SignupPage(cardLayout, mainPanel), SIGN);
    mainPanel.add(new LoginPage(cardLayout, mainPanel), LOGIN);
    
    mainPanel.add(menuPanel, "PLAY"); 
    mainPanel.add(gamePanel, "GAME");
    
   
    mainPanel.add(skinPanel, "SKINS"); 
  

    add(mainPanel);
}

    public void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }

    public void setDifficulty(Difficulty d) {
        this.currentDifficulty = d;
    }

    public Difficulty getDifficulty() {
        return currentDifficulty;
    }
}