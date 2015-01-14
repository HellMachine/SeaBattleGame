package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

public class Boat extends Ship{

    Boat (int x, int y){
        shipCell = new Cell[BOAT_CELL];
        shipCell[0] = new Cell(x, y, this);
    }

    @Override
    public String toString(){
        return "Boat";
    }
}
