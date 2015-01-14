package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

public class Destroyer extends Ship{

    Destroyer(int x, int y, int x1, int y1){
        shipCell = new Cell[DESTROYER_CELL];
        shipCell[0] = new Cell(x, y, this);
        shipCell[1] = new Cell(x1, y1, this);
    }

    @Override
    public String toString(){
        return "Destroyer";
    }
}
