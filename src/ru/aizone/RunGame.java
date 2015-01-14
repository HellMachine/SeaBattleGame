package ru.aizone;

import ru.aizone.gui.LoginUI;
import ru.aizone.gui.MainUI;
import javax.swing.*;
import java.awt.*;

/**
 * @author: lxmikh@gmail.com
 */

public class RunGame {

    private static LogicSystem game = new LogicSystem();

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                game.runLogic();
                createLoginUI();
            }
        });
        game.startGame();
    }

    public static void createAndShow() {

        MainUI gameFrame = new MainUI();
        gameFrame.drawGameField(game.getPlayerField(), game.getComputerField());
        game.setMainFrameSwing(gameFrame);
        centerFrame(gameFrame);
    }

    private static void createLoginUI(){

        JFrame frame = new LoginUI("Bаше имя?");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200,100);
        centerFrame(frame);
        frame.setResizable(false);
    }

    public static void centerFrame(Window frame) {

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        if (x < 0) {
            x = 0;
        }
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        if (y < 0) {
            y = 0;
        }
        frame.setBounds(x, y, frame.getWidth(), frame.getHeight());
    }
}
