import java.awt.*;
import javax.swing.*;


public class MainPanel extends JPanel {

    JButton signup = new JButton("SIGN UP");
    JButton login = new JButton("LOG IN");
    JLabel title = new JLabel("SNACK-A-MOLE");
    JLabel carotMohsin = new JLabel();
    ImageIcon crohsin = new ImageIcon("zain.png");
    
    CardLayout cardLayout;
    JPanel cards;

    public MainPanel(CardLayout cardLayout, JPanel cards) {
        this.cardLayout = cardLayout;
        this.cards = cards;
    



          Image image = crohsin.getImage();
          Image newimg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); 
          ImageIcon newIcon = new ImageIcon(newimg);
          carotMohsin.setIcon(newIcon); 
      

        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(60, 179, 113)); // dark background
        this.setOpaque(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

       
        title.setFont(new Font("Arial", Font.BOLD, 67));
        title.setForeground(Color.WHITE);

        signup.setBackground(Color.WHITE);
        login.setBackground(Color.WHITE);

       
        Font buttonFont = new Font("Monospaced", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(250, 80); 

        signup.setFont(buttonFont);
        signup.setPreferredSize(buttonSize);
        login.setFont(buttonFont);
        login.setPreferredSize(buttonSize);

       
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 50, 0); 
        this.add(title, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0); 
        this.add(signup, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(login, gbc);

        gbc.gridy = 3; 
        gbc.insets = new Insets(20, 0, 0, 0); 


         this.add(carotMohsin, gbc);

       
        signup.addActionListener(e -> cardLayout.show(cards, App.SIGN));
        login.addActionListener(e -> cardLayout.show(cards, App.LOGIN));
    }
}