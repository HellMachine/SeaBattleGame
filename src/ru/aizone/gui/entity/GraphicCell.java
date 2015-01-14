package ru.aizone.gui.entity;

import ru.aizone.LogicSystem;
import ru.aizone.entity.Cell;
import ru.aizone.gui.MainUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author: lxmikh@gmail.com
 */

public class GraphicCell extends JComponent implements MouseListener {

    public static final int SIZE_OF_PRINTED_CELL = 16;

    public static int WIDTH = 16;
    public static int HEIGHT = 16;

    private int i;
    private int j;

    private boolean isSelected = false;
    private GraphicField field;

    private boolean isAttacked;
    private boolean isShip;

    private Cursor attackCursor;

    public GraphicCell(GraphicField field, int x, int y) {
        isSelected = false;
        isAttacked = false;
        i = x;
        j = y;
        this.field = field;
        this.field.add(this);
        setSize(WIDTH, HEIGHT);

        setLocation(GraphicField.HEADER_ZONE + GraphicField.SPACE_BETWEEN * 2 +
                (WIDTH + GraphicField.SPACE_BETWEEN) * x,
                GraphicField.HEADER_ZONE + GraphicField.SPACE_BETWEEN * 2 +
                        (HEIGHT + GraphicField.SPACE_BETWEEN) * y);

        addMouseListener(this);
    }

    public static GraphicCell swingCellCreate(GraphicField field, int x, int y) {
        if(field != null && 0 <= x && x < GraphicField.SECTOR_COUNT && 0 <= y && y < GraphicField.SECTOR_COUNT)
            return new GraphicCell(field, x, y);
        else
            return null;
    }

    public Cursor setCustomCursor(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image imgCursorAttack = toolkit.getImage(getClass().getResource("./../img/attackCur.png"));
        attackCursor = toolkit.createCustomCursor(imgCursorAttack, new Point(0, 0), "attack");
        return attackCursor;
    }

    public void setShip() {
        isShip = true;
    }

    public boolean isShip() {
        return isShip;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        Image shipImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../img/ship.png"));
        Image attackedShip = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../img/attackedShip.png"));
        Image deadShip = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../img/deadShip.png"));
        Image seaImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../img/blank.png"));
        Image seaImgAfterAttackVoid = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../img/blank_void.png"));
        Image scopeImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./../img/scopeImg.png"));

        if(field.isVisible()) {
            Cell cell = field.getField().getLogicField()[i][j];
            if(isShip() && cell.getShip().shipIsDead()) {
                drawI(g2d, deadShip);
            } else if(isShip() && isAttacked) {
                drawI(g2d, attackedShip);
            } else if(isShip()){
                drawI(g2d, shipImg);
                //FIXME Условие для сокрытия кораблей компьютера на поле
//            } else if(isShip() && !(field.isVisible())){
//                drawI(g2d, seaImg);
            } else if(isAttacked) {
                drawI(g2d, seaImgAfterAttackVoid);
            } else {
                drawI(g2d, seaImg);
            }
        } else if(isAttacked ){
            Cell cell = field.getField().getLogicField()[i][j];
            if(isShip() && cell.getShip().shipIsDead()) {
                drawI(g2d, deadShip);
            } else if(isShip()) {
                drawI(g2d, attackedShip);
            } else {
                drawI(g2d, seaImgAfterAttackVoid);
            }

        } else {
            if(isSelected) {

                //TODO не работают методы, ни один, ни второй!
                //drawI(g2d, scopeImg);
                //setCursor(setCustomCursor());
                drawI(g2d, seaImg);
            }
        }
    }

    public void drawI(Graphics2D g, Image img) {
        g.drawImage(img, 0, 0, this);
    }

    public void setAttacked() {
        this.isAttacked = true;
    }

    public void mouseEntered(MouseEvent e) {
        isSelected = true;
        MainUI.getLogArea().setText("Mouse Entered\n" + e.toString() + "\n");

        Cell cell = field.getField().getLogicField()[i][j];
        MainUI.getLogArea().append(field.getField().toString());
        MainUI.getLogArea().append("\n[x] = " + cell.getX() + "\n");
        MainUI.getLogArea().append("[y] = " + cell.getY() + "\n");
        MainUI.getLogArea().append("isShip = " + cell.isShip() + "\n");
        MainUI.getLogArea().append("ship = " + cell.getShip() + "\n");
        MainUI.getLogArea().append("[wFired] = " + cell.isWasFired() + "\n");

        MainUI.getLogArea().setLineWrap(true);

        field.setSelected(this);
        repaint();
    }

    public void mouseExited(MouseEvent e) {
        isSelected = false;
        field.setSelected(null);
        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        if(!isAttacked) {
            setAttacked();
        }

        Cell cell = field.getField().getLogicField()[i][j];
        cell.setWasFired(true);
        field.setSelected(null);

        if (cell.isShip()) {
            setShip();
            LogicSystem.playerHit = true;
        }

        repaint();
        LogicSystem.playerWasShoot = true;

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }


}

