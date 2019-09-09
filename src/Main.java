import org.omg.IOP.ENCODING_CDR_ENCAPS;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static final int WIDTH = 1300;
    private static final int HEIGHT = 750;

    public static void main(String[] args) {

        JFrame window = new JFrame("Golazo");
        window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        window.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.add(new Engine());
        window.setVisible(true);

    }
}

