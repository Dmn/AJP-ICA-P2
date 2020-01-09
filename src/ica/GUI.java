package ica;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The following class is to display the Monitor.
 * @author v8039087
 */
public class GUI extends JFrame {

    /**
     * JTextArea is where the text will be displayed. JPanel to get the view.
     * Monitor is the monitor that is linked with the GUI.
     */
    private final JTextArea answer;
    private final JPanel panel;
    private final Monitor monitor;

    /**
     * Setting up the JFRAME for the monitor.
     * @param name the name for the monitor.
     * @param mon monitor associated with the GUI.
     */
    public GUI(String name, Monitor mon) {

        monitor = mon;
        answer = new JTextArea(30, 30);
        JScrollPane scroll = new JScrollPane(answer);
        answer.setLineWrap(true);
        answer.setEditable(false);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JLabel("Answer:"), BorderLayout.PAGE_START);
        panel.add(scroll, BorderLayout.CENTER);
        show(name);
    }

    /**
     * Setting up the JFRAME for the monitor.
     */
    private void show(String name) {
        this.setTitle("Monitor Name: " + name);
        Container container = this.getContentPane();
        this.add(panel);
        this.pack();
        this.setSize(700, 200);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * closes the GUI.
     */
    public void close() {
        monitor.CloseGUI();
    }

    /**
     * Add a line to the answer.
     * @param line the line where output would be added.
     */
    public void display(String line) {
        answer.append(line);
    }
}
