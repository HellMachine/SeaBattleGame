package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

public class Battleship extends Ship{

    Battleship(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3){
        shipCell = new Cell[BATTLESHIP_CELL];
        shipCell[0] = new Cell(x, y, this);
        shipCell[1] = new Cell(x1, y1, this);
        shipCell[2] = new Cell(x2, y2, this);
        shipCell[3] = new Cell(x3, y3, this);
    }

    @Override
    public String toString(){
        return "Battleship";
    }
}
