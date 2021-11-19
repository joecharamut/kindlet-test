package rocks.spaghetti.kindletest.ui;

import rocks.spaghetti.kindletest.Icons;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    private final JPanel root;
    private final JTabbedPane tabs;

    public MainUI() {
        root = new JPanel();
        root.setLayout(new BorderLayout());
        root.setPreferredSize(UIConstants.DISPLAY_SIZE);

        tabs = new JTabbedPane(SwingConstants.BOTTOM);
        tabs.setBorder(BorderFactory.createEmptyBorder());

//        tabs.addTab("Clock", Icons.CLOCK_ICON, new JPanel());
        root.add(tabs, BorderLayout.CENTER);
    }

    public Component getRootComponent() {
        return root;
    }


}
