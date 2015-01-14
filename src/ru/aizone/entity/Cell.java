package ru.aizone.entity;

/**
 * @author: lxmikh@gmail.com
 */

public class Cell {

    private int x, y;
    private Ship ship;
    private boolean wasFired;

    public Cell(int x, int y, Ship ship){
        this.x = x;
        this.y = y;
        this.ship = ship;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setWasFired(boolean wasFired) {
        this.wasFired = wasFired;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean isWasFired() {
        return wasFired;
    }

    public boolean isShip(){
        return ship != null;
    }
}
