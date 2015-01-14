package ru.aizone.gui;

import ru.aizone.entity.Field;
import ru.aizone.gui.entity.GraphicField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: lxmikh@gmail.com
 */

public class MainUI extends JFrame{

    private static GraphicField playerField;
    private static GraphicField computerField;

    private static JTextArea logArea = new JTextArea();

    public MainUI() {
        setTitle("SeaBattle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750,300);

        //FIXME Trouble here! If param == false then field become FULL invisible
        playerField =  new GraphicField(true);
        computerField = new GraphicField(true);

        paintField();
        setJMenuBar(addMenu());

        setVisible(true);
        setResizable(false);

    }

    public JMenuBar addMenu() {

        JMenu menu, menu2;
        JMenuItem menuItem;
        JMenuBar menuBar = new JMenuBar();

        //Первое меню
        menu = new JMenu("Игра");
        menuBar.add(menu);

        menuItem = new JMenuItem("В бой!", createIcon("img/rePlay.png"));
        menu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //TODO доделать рестарт
                System.out.println("Item clicked: " + e.getActionCommand());
            }
        });
        menu.addSeparator();


        menuItem = new JMenuItem("Выход", createIcon("img/Close.png"));
        menu.add(menuItem);

        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Item clicked: " + e.getActionCommand());
                System.exit(0);
            }
        });

        //Второе меню
        menuBar.add(Box.createHorizontalGlue());
        menu2 = new JMenu("?");
        menuItem = new JMenuItem("О игре");
        menuBar.add(menu2);
        menu2.add(menuItem);

        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Item clicked: " + e.getActionCommand());
                JOptionPane.showOptionDialog(getComponent(0),
                        "SeaBattle Game \n ver.00001a", "About",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            }
        });
        return menuBar;
    }

    public void paintField() {

        JPanel mainPanel = new JPanel();
        JPanel playerPanel = new JPanel();
        JPanel computerPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(1, 0));
        mainPanel.add(playerPanel);
        mainPanel.add(computerPanel);
        mainPanel.add(logArea);

        playerPanel.setLayout(new GridLayout(1, 0));
        playerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder
                (LoginUI.getPlayerName() + " Field"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        playerPanel.add(playerField);

        computerPanel.setLayout(new GridLayout(1, 0));
        computerPanel.setBorder(BorderFactory.createCompoundBorder
                (BorderFactory.createTitledBorder("Computer Field"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        computerPanel.add(computerField);

        //Логирование ходов
        logArea.setLayout(new GridLayout(1,0));
        logArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Logging"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        logArea.setAutoscrolls(true);
        mainPanel.add(logArea);

        setContentPane(mainPanel);
    }

    public static GraphicField getPlayerField() {
        return playerField;
    }

    public static GraphicField getComputerField() {
        return computerField;
    }

    public void drawGameField(Field playerFieldArr, Field computerFieldArr) {
        playerField.setField(playerFieldArr);
        computerField.setField(computerFieldArr);
        playerField.printField();
        computerField.printField();
    }

    public static JTextArea getLogArea(){
        return logArea;
    }

    public ImageIcon createIcon(String path) {

        java.net.URL imgURL = MainUI.class.getResource(path);

        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}