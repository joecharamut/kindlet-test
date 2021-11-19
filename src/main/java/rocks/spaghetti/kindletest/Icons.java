package rocks.spaghetti.kindletest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

public class Icons {
    public static ImageIcon CLOCK_ICON;

    private Icons() {
        throw new IllegalStateException("Utility Class");
    }

    public static void loadIcons() {
        CLOCK_ICON = loadIcon("icons/icons8-clock-24.png");
    }

    private static ImageIcon loadIcon(String path) {
        try {
            return new ImageIcon(ImageIO.read(ResourceLoader.getResource(path)));
        } catch (IOException e) {
            return null;
        }
    }
}
