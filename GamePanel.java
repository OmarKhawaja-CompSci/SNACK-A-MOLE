import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends BaseScreen implements ActionListener {
    
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JPanel gridPanel;
    private JButton[] moleButtons;
    private MoleType currentMoleType = MoleType.NORMAL;
    static MusicManager music = new MusicManager();
    
    private Timer gameTimer;
    private Timer moleTimer;
    
    private int score = 0;
    private int timeLeft = 30;
    private int currentMoleIndex = -1;
    private Random random;

    private ImageIcon moleIcon;
    private ImageIcon moleIcon2;
    private ImageIcon moleIcon3;
    private ImageIcon holeIcon;
    private ImageIcon crohsin;

    public GamePanel(MainFrame mainFrame, ScoreManager scoreManager) {
        super(mainFrame, scoreManager);
        setLayout(new BorderLayout());

        random = new Random();

        moleIcon = loadSprite("mole.png", 350, 200);
        moleIcon2 = loadSprite("mole2.png", 350, 200);
        moleIcon3 = loadSprite("mole3.png", 350, 200);
        holeIcon = loadSprite("hole.png", 350, 200);
        crohsin = loadSprite("crohsin.png", 350, 200);


        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        timeLabel = new JLabel("Time: 30");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> {
            stopGame();
            music.stop();
        });

        topPanel.add(scoreLabel);
        topPanel.add(timeLabel);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);


        gridPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        moleButtons = new JButton[9];

        for (int i = 0; i < 9; i++) {
            JButton btn = createMoleButton(i);
            moleButtons[i] = btn;
            gridPanel.add(btn);
        }
        add(gridPanel, BorderLayout.CENTER);


        gameTimer = new Timer(1000, e -> updateTime());
        moleTimer = new Timer(1000, this); 

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                resetGame();
            }
            @Override
            public void componentHidden(ComponentEvent e) {
                stopTimers();
            }
        });
        CustomCursor.apply(this);

        topPanel.setBackground(new Color(8, 143, 255)); 
        gridPanel.setBackground(new Color(21, 255, 8)); 
    }

    private JButton createMoleButton(int index) {
        JButton btn = new JButton();
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        setButtonState(btn, false);

        btn.addActionListener(e -> {
            if (index == currentMoleIndex) {
                int multiplier = mainFrame.getDifficulty().getScoreMultiplier();

                if (currentMoleType == MoleType.BAD) {
                    score -= 2 * multiplier;  // penalty
                    if (score < 0) score = 0; 
                } else {
                    score += 1 * multiplier;
                }

                scoreLabel.setText("Score: " + score);
                setButtonState(btn, false);
                currentMoleIndex = -1;
            }
        });
        return btn;
    }
    
    private void setButtonState(JButton btn, boolean isMole) {
        if (isMole) {
            if (currentMoleType == MoleType.BAD && crohsin != null) {
                btn.setIcon(crohsin);
                btn.setText("");
                return;
            }

            
            int randomNum = random.nextInt(100);
            if (randomNum < 34) btn.setIcon(moleIcon);
            else if (randomNum < 68) btn.setIcon(moleIcon2);
            else btn.setIcon(moleIcon3);
        } else {
            btn.setIcon(holeIcon);
            btn.setText("");
        }
    }

    private void resetGame() {
        
        Difficulty diff = mainFrame.getDifficulty();
        CustomCursor.apply(this);
        score = 0;
        timeLeft = 30;
        scoreLabel.setText("Score: 0");
        timeLabel.setText("Time: 30");
        
        
        moleTimer.setDelay(diff.getSpeedDelay());
        
        for(JButton btn : moleButtons) setButtonState(btn, false);
        
        gameTimer.start();
        moleTimer.start();
        music.play("menu.wav");
    }

    private void stopGame() {
        stopTimers();
        scoreManager.saveHighScore(score);
        JOptionPane.showMessageDialog(this, "Game Over!\nDifficulty: " + mainFrame.getDifficulty() + "\nFinal Score: " + score);
        mainFrame.showScreen("PLAY");
        music.stop();
    }

    private void stopTimers() {
        if (gameTimer != null){ 
            gameTimer.stop();
            music.stop();   
        }
        if (moleTimer != null) moleTimer.stop();
    }

    private void updateTime() {
        timeLeft--;
        timeLabel.setText("Time: " + timeLeft);
        if (timeLeft <= 0) {
            stopGame();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentMoleIndex != -1) {
            setButtonState(moleButtons[currentMoleIndex], false);
        }

        currentMoleIndex = random.nextInt(9);

   
        currentMoleType = (random.nextInt(100) < 20)
                ? MoleType.BAD
                : MoleType.NORMAL;

        setButtonState(moleButtons[currentMoleIndex], true);
    }
}