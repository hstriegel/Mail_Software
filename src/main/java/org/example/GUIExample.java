package org.example;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Emails");
        JLabel l1 = new JLabel("1");
        l1.setHorizontalAlignment(JLabel.CENTER);
        Container pane = frame.getContentPane();
        pane.add(l1, BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(new Button("Button1"));
        p.add(new Button("Button2"));
        p.add(new Button("Button3"));
        p.add(new Button("Button4"));
        p.add(new Button("Button5"));
        p.add(new Button("Button6"));

        JMenuBar menu = new JMenuBar();
        JMenu m = new JMenu("Menu");
        menu.add(m);
        frame.setJMenuBar(menu);
        JMenuItem mi = new JMenuItem("Item");
        menu.add(mi);
        mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("a");
            }
        });
        JMenu a = new JMenu("a");
        menu.add(a);


        JScrollPane scrollablebuttons = new JScrollPane(p);
        scrollablebuttons.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollablebuttons.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pane.add(scrollablebuttons, BorderLayout.WEST);
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
