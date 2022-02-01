package Views;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public AppFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        setVisible(true);
    }
}
