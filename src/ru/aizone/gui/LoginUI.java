package ru.aizone.gui;

import ru.aizone.RunGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author: lxmikh@gmail.com
 */

public class LoginUI extends JFrame {

    private static String pName;
    JTextField nameField = new JTextField(10);

    public LoginUI(String title) {
        super(title);
        super.setVisible(true);
        addComponents();
    }

    public void addComponents(){

        JLabel nameLabel = new JLabel("Ваше имя:");
        JButton b = new JButton("В бой ->");
        JPanel userPanel = new JPanel();

        userPanel.setLayout(new FlowLayout());
        userPanel.add(nameLabel);
        userPanel.add(nameField);
        b.setMnemonic(KeyEvent.VK_ENTER);
        userPanel.add(b);

        setContentPane(userPanel);

        b.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                buttonClickAction();
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });

        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                buttonClickAction();
            }
        });

    }

    private void buttonClickAction(){
        pName = nameField.getText();
        RunGame.createAndShow();
        this.setVisible(false);
    }

    public static String getPlayerName() {
        return pName;
    }
}