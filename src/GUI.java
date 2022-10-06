import javax.swing.*;
import java.awt.*;

public class GUI {
    JLabel label = new JLabel("Service is running on port 8011");
    JButton button = new JButton();


    public GUI() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout());
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Notify Translator");
        frame.pack();
        frame.setVisible(true);
    }

    }

