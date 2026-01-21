import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends BaseScreen {
    static MusicManager music = new MusicManager();
    private ImageIcon crohsin;
    
    public MenuPanel(MainFrame mainFrame, ScoreManager scoreManager) {
        super(mainFrame, scoreManager);
        setLayout(new GridBagLayout());
        setBackground(new Color(8, 143, 255)); 
        crohsin = loadSprite("crohsin.png", 350, 200);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.gridx = 0;
        gbc.gridy = 0;

       

    
        JLabel titleLabel = new JLabel("SNACK-A-MOLE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, gbc);

       
        gbc.gridy++;
        JPanel diffPanel = new JPanel();
        diffPanel.setOpaque(false);
        JLabel diffLabel = new JLabel("Difficulty: ");
        diffLabel.setForeground(Color.WHITE);
        diffLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        JComboBox<Difficulty> diffBox = new JComboBox<>(Difficulty.values());
        diffBox.setSelectedItem(Difficulty.MEDIUM); 
        diffBox.addActionListener(e -> mainFrame.setDifficulty((Difficulty) diffBox.getSelectedItem()));

        diffPanel.add(diffLabel);
        diffPanel.add(diffBox);
        add(diffPanel, gbc);

  
        gbc.gridy++;
        JButton playButton = new JButton("Start Game");
        playButton.setFont(new Font("Arial", Font.PLAIN, 24));
        playButton.addActionListener(e -> {
            mainFrame.showScreen("GAME");
        });
        add(playButton, gbc);


       // Skins Button
       gbc.gridy++;
       JButton skinsButton = new JButton("Cursor Skins");
       skinsButton.setFont(new Font("Arial", Font.PLAIN, 24));
       skinsButton.addActionListener(e -> mainFrame.showScreen("SKINS"));
       add(skinsButton, gbc);

        // Exit Button
        gbc.gridy++;
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton, gbc);

        // High Score
        gbc.gridy++;
        JLabel scoreLabel = new JLabel("Current High Score: " + scoreManager.loadHighScore());
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(scoreLabel, gbc);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                scoreLabel.setText("Current High Score: " + scoreManager.loadHighScore());
            }
        });
    }
}