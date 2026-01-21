import java.awt.*;
import javax.swing.*;


public class CursorSelectionPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;

   
    public CursorSelectionPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(60, 179, 113)); 

 
        JLabel title = new JLabel("CHOOSE YOUR CURSOR", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(title, BorderLayout.NORTH);

  
        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;

      
        for (CustomCursor.Skin skin : CustomCursor.Skin.values()) {
            JPanel card = createSkinCard(skin);
            grid.add(card, gbc);
            
            gbc.gridx++;
            if (gbc.gridx > 2) { 
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        add(grid, BorderLayout.CENTER);


        JButton backBtn = new JButton("BACK TO MENU");
        backBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backBtn.setPreferredSize(new Dimension(200, 60));
        
       
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "PLAY"));
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        bottomPanel.add(backBtn);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createSkinCard(CustomCursor.Skin skin) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 255, 255, 200));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        panel.setPreferredSize(new Dimension(180, 200));

      
        ImageIcon icon = loadIcon(skin.normalPath, 64, 64);
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(skin.name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton selectBtn = new JButton("SELECT");
        selectBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectBtn.addActionListener(e -> {
            CustomCursor.changeSkin(skin);
           
        });

     
        panel.add(Box.createVerticalStrut(20));
        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(selectBtn);
        panel.add(Box.createVerticalStrut(20));

        return panel;
    }

    
    private ImageIcon loadIcon(String path, int width, int height) {
        try {
            java.net.URL imgURL = getClass().getResource(path);
            if (imgURL != null) {
                ImageIcon original = new ImageIcon(imgURL);
                Image img = original.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            } else {
                return null; 
            }
        } catch (Exception e) {
            return null;
        }
    }
}