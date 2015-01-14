package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

public class Ship {

    //Кол-во клеток, занимаемых кораблями(Размер корабля)
    public static final int BOAT_CELL = 1;
    public static final int DESTROYER_CELL = 2;
    public static final int CRUSER_CELL = 3;
    public static final int BATTLESHIP_CELL = 4;

    //Кол-во кораблей по видам
    public static final int BOAT_COUNT = 4;
    public static final int DESTROYER_COUNT = 3;
    public static final int CRUSER_COUNT = 2;
    public static final int BATTLESHIP_COUNT = 1;

    //Общее кол-во клеток, занимаемых кораблями.
    //Можно использовать для определения победы
    public static final int SUM_COUNT_CELL_W_SHIP = BOAT_COUNT + DESTROYER_COUNT * DESTROYER_CELL +
            BATTLESHIP_CELL + CRUSER_COUNT * Ship.CRUSER_CELL;

    //shipCell - элемент "массива корабля" = 1 ячейка корабля
    public Cell[] shipCell;

    public boolean shipIsDead() {
        for(Cell cell : shipCell) {
            if(!cell.isWasFired()) {
                return false;
            }
        }
        return true;
    }
}
