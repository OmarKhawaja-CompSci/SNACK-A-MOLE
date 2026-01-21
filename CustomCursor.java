import java.awt.*;
import java.awt.event.*;

public class CustomCursor {


    public enum Skin {
        CLASSIC("Classic", "/cursor.png", "/cursor2.png"),
        GAUNTLET("Infinity Gauntlet", "gauntlet.png", "gauntlet2.png"),
        AMOGUS("Among Us", "amogus.png", "amogus2.png");

        final String name;
        final String normalPath;
        final String clickPath;

        Skin(String name, String normalPath, String clickPath) {
            this.name = name;
            this.normalPath = normalPath;
            this.clickPath = clickPath;
        }
    }

    private static Cursor normalCursor;
    private static Cursor clickCursor;
    

    private static Skin currentSkin = Skin.CLASSIC;

   
    private static Cursor createCursor(String path, String name) {
        try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            java.net.URL imgURL = CustomCursor.class.getResource(path);
            
            if (imgURL == null) {
                
                return Cursor.getDefaultCursor();
            }
            
            Image img = toolkit.getImage(imgURL);
            Point hotspot = new Point(16, 16);
            return toolkit.createCustomCursor(img, hotspot, name);
        } catch (Exception e) {
            return Cursor.getDefaultCursor();
        }
    }

    public static void changeSkin(Skin skin) {
        currentSkin = skin;
        normalCursor = createCursor(skin.normalPath, "Normal");
        clickCursor = createCursor(skin.clickPath, "Click");
    }

    public static void apply(Component c) {
     
        if (normalCursor == null) {
            changeSkin(currentSkin);
        }

        c.setCursor(normalCursor);

    
        for (MouseListener ml : c.getMouseListeners()) {
            if (ml.toString().contains("CustomCursor")) {
                c.removeMouseListener(ml);
            }
        }

        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (clickCursor != null) c.setCursor(clickCursor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (normalCursor != null) c.setCursor(normalCursor);
            }
            
           
            @Override
            public String toString() {
                return "CustomCursorListener";
            }
        });

    
        if (c instanceof Container container) {
            for (Component child : container.getComponents()) {
                apply(child);
            }
        }
    }
    
    public static Skin getCurrentSkin() {
        return currentSkin;
    }
}