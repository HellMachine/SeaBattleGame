package ru.aizone.gui.entity;

import ru.aizone.entity.Cell;
import ru.aizone.entity.Field;
import ru.aizone.gui.MainUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author: lxmikh@gmail.com
 */

public class GraphicField extends JComponent {
    public static int SECTOR_COUNT = 10;
    public static int SPACE_BETWEEN = 2;
    public static int HEADER_ZONE = 20;

    public static int HEIGHT = HEADER_ZONE + SPACE_BETWEEN * 2 + (GraphicCell.SIZE_OF_PRINTED_CELL + SPACE_BETWEEN) * (SECTOR_COUNT + 1);
    public static int WIDTH = HEADER_ZONE + SPACE_BETWEEN * 2 + (GraphicCell.SIZE_OF_PRINTED_CELL + SPACE_BETWEEN) * (SECTOR_COUNT + 1);

    private GraphicCell[][] swingCells;
    private GraphicCell selectedCell;

    private Field field;
    //private MainUI mainFrameSwing;

    private boolean isVisible;

    public GraphicField(boolean isVisible) {
        this.isVisible = isVisible;
        //this.mainFrameSwing = mainFrameSwing;

        swingCells = new GraphicCell[SECTOR_COUNT][SECTOR_COUNT];
        for (int i = 0; i < SECTOR_COUNT; i++)
            for (int j = 0; j < SECTOR_COUNT; j++)
                swingCells[i][j] = GraphicCell.swingCellCreate(this, i, j);

        selectedCell = null;

        setSize(WIDTH, HEIGHT);
        printLettersAndNumbers();
    }

    public GraphicCell[][] getSwingCells() {
        return this.swingCells;
    }

    public Field getField() {
        return this.field;
    }

    public void setField(Field field) {
        this.field = field;
    }

//    public void paint(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g;
//        paintGradientBoxForFieldBottom(g2d);
//        super.paint(g);
//    }
//
//    private void paintGradientBoxForFieldBottom(Graphics2D g) {
//        int lw = getWidth() - 1;
//        int lh = getHeight() - 1;
//        int rw = SPACE_BETWEEN * 2;
//        int rh = SPACE_BETWEEN * 2;
//
//        GradientPaint paint = new GradientPaint(lw, lh, Color.white, lw / 4, lh / 4, Color.lightGray, true);
//        g.setPaint(paint);
//        g.fillRoundRect(0, 0, lw, lh, rw, rh);
//        g.setColor(Color.lightGray);
//        g.drawRoundRect(0, 0, lw, lh, rw, rh);
//    }

    public void setSelected(GraphicCell graphicCell) {
        selectedCell = graphicCell;
        repaint();
    }

    public boolean isVisible() {
        return isVisible;
    }

    private void printLettersAndNumbers() {
        char[] letters = {'А', 'B', 'C', 'D', 'E', 'F', 'G', 'I', 'J', 'K'};
        for (int i = 0; i < SECTOR_COUNT; i++) {
            JLabel horizontal = new JLabel(String.valueOf(i + 1));
            if (i < letters.length)
                horizontal.setText(String.valueOf(letters[i]));
            JLabel vertical = new JLabel(String.valueOf(i + 1));
            horizontal.setHorizontalAlignment(JLabel.CENTER);
            horizontal.setVerticalAlignment(JLabel.CENTER);
            vertical.setHorizontalAlignment(JLabel.CENTER);
            vertical.setVerticalAlignment(JLabel.CENTER);
            horizontal.setSize(GraphicCell.SIZE_OF_PRINTED_CELL, HEADER_ZONE);
            vertical.setSize(HEADER_ZONE, GraphicCell.SIZE_OF_PRINTED_CELL);
            horizontal.setLocation(HEADER_ZONE + SPACE_BETWEEN * 2 +
                    (GraphicCell.SIZE_OF_PRINTED_CELL + SPACE_BETWEEN) * i, SPACE_BETWEEN);
            vertical.setLocation(SPACE_BETWEEN, HEADER_ZONE + SPACE_BETWEEN * 2 +
                    (GraphicCell.SIZE_OF_PRINTED_CELL + SPACE_BETWEEN) * i);
            add(horizontal);
            add(vertical);
        }
    }

    public void printField() {
        Cell[][] cells = this.field.getLogicField();
        for(int i = 0; i < SECTOR_COUNT; i++) {
            for(int j = 0; j < SECTOR_COUNT; j++) {
                if(cells[i][j].isShip()) {
                    swingCells[i][j].setShip();
                    swingCells[i][j].repaint();
                } else if(cells[i][j].isWasFired()) {
                    swingCells[i][j].setAttacked();
                }
            }
        }
    }


}
