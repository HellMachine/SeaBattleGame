package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

import java.util.Random;

public class Field {

    //Размер поля FIELD_ROW - кол-во рядов; FIELD_COL - кол-во столбцов
    private static final int FIELD_ROW = 10;
    private static final int FIELD_COL = 10;
    public  Cell[][] field = new Cell[FIELD_ROW][FIELD_COL];

    public void fieldGenerate() {

        for (int i = 0; i < FIELD_ROW; i++) {
            for (int j = 0; j < FIELD_COL; j++) {
                Cell cell = new Cell(i, j, null);
                field[i][j] = cell;
            }
        }
        addBattleships();
        addCrusers();
        addDestroyers();
        addBoats();
    }

    //Методы, создающие экземпляры кораблей
    public void createBoat(int x, int y) {
        Boat boat = new Boat(x, y);
        field[x][y] = boat.shipCell[0];
    }

    public void createDestroyer(int x1, int y1, int x2, int y2) {
        Destroyer destr = new Destroyer(x1, y1, x2, y2);
        field[x1][y1] = destr.shipCell[0];
        field[x2][y2] = destr.shipCell[1];
    }

    public void createCruser(int x1, int y1, int x2, int y2, int x3, int y3) {
        Cruser cruser = new Cruser(x1, y1, x2, y2, x3, y3);
        field[x1][y1] = cruser.shipCell[0];
        field[x2][y2] = cruser.shipCell[1];
        field[x3][y3] = cruser.shipCell[2];
    }

    public void createBattlesip(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        Battleship bs = new Battleship(x1, y1, x2, y2, x3, y3, x4, y4);
        field[x1][y1] = bs.shipCell[0];
        field[x2][y2] = bs.shipCell[1];
        field[x3][y3] = bs.shipCell[2];
        field[x4][y4] = bs.shipCell[3];
    }

    //Методы добавления кораблей на игровое поле
    public void addBoats() {
        int countShip = 0;
        do {
            int x = genRNDCoord();
            int y = genRNDCoord();
            if (!shipInCell(x, y, false, Ship.BOAT_CELL)) {
                createBoat(x, y);
                countShip++;
            }
        } while (countShip < Ship.BOAT_COUNT);
    }

    public void addDestroyers() {
        int x2, y2;
        int countShip = 0;

        do {
            boolean wasRotate = shipRotation();
            int x1 = genRNDCoord();
            int y1 = genRNDCoord();
            if (wasRotate) {
                x2 = x1 + 1;
                y2 = y1;
            } else {
                x2 = x1;
                y2 = y1 + 1;
            }

            if ((x2 != 10) && (y2 != 10))
                if (!shipInCell(x1, y1, wasRotate, Ship.DESTROYER_CELL)) {
                    createDestroyer(x1, y1, x2, y2);
                    countShip++;
                }

        } while (countShip < Ship.DESTROYER_COUNT);
    }

    public void addCrusers() {
        int x2, y2, x3, y3;
        int countShip = 0;

        do {
            boolean wasRotate = shipRotation();
            int x1 = genRNDCoord();
            int y1 = genRNDCoord();
            if (wasRotate) {
                x2 = x1 + 1;
                x3 = x2 + 1;
                y3 = y2 = y1;
            } else {
                x3 = x2 = x1;
                y2 = y1 + 1;
                y3 = y2 + 1;
            }

            if ((x2 != 10) && (y2 != 10) && (x3 != 10) && (y3 != 10))
                if (!shipInCell(x1, y1, wasRotate, Ship.CRUSER_CELL)) {
                    createCruser(x1, y1, x2, y2, x3, y3);
                    countShip++;
                }

        } while (countShip < Ship.CRUSER_COUNT);
    }

    public void addBattleships() {
        int x2, y2, x3, y3, x4, y4;
        int countShip = 0;

        do {
            boolean wasRotate = shipRotation();
            int x1 = genRNDCoord();
            int y1 = genRNDCoord();
            if (wasRotate) {
                x2 = x1 + 1;
                x3 = x2 + 1;
                x4 = x3 + 1;
                y4 = y3 = y2 = y1;
            } else {
                x4 = x3 = x2 = x1;
                y2 = y1 + 1;
                y3 = y2 + 1;
                y4 = y3 + 1;
            }

            if ((x2 != 10) && (y2 != 10) && (x3 != 10) && (y3 != 10) && (x4 != 10) && (y4 != 10))
                if (!shipInCell(x1, y1, wasRotate, Ship.BATTLESHIP_CELL)) {
                    createBattlesip(x1, y1, x2, y2, x3, y3, x4, y4);
                    countShip++;
                }

        } while (countShip < Ship.BATTLESHIP_COUNT);
    }

    public static int genRNDCoord() {
        Random rndC = new Random();
        return rndC.nextInt(10);
    }

    public static boolean shipRotation() {
        return new Random().nextBoolean();
    }

    public boolean shipInCell(int x, int y, boolean wasRotate, int shipSize) {
        int startX, startY;
        int i, j;

        if (wasRotate) {
            i = shipSize;
            j = 3;
        } else {
            i = 3;
            j = shipSize;
        }

        for (startX = -1; startX <= i; startX++) {
            for (startY = -1; startY <= j; startY++) {
                if ((x + startX) >= 0 && (y + startY) >= 0 && (x + startX) <= 9 && (y + startY) <= 9) {
                    if (field[x + startX][y + startY].isShip()) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    public Cell[][] getLogicField() {
        return field;
    }

}
