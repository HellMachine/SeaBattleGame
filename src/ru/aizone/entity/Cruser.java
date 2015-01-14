package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

public class Cruser extends Ship{

    Cruser(int x, int y, int x1, int y1, int x2, int y2){
        shipCell = new Cell[CRUSER_CELL];
        shipCell[0] = new Cell(x, y, this);
        shipCell[1] = new Cell(x1, y1, this);
        shipCell[2] = new Cell(x2, y2, this);
    }

    @Override
    public String toString(){
        return "Cruser";
    }
}
