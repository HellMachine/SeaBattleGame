package ru.aizone;

import ru.aizone.entity.Cell;
import ru.aizone.entity.Field;
import ru.aizone.entity.Ship;
import ru.aizone.gui.MainUI;
import ru.aizone.gui.entity.GraphicCell;
import ru.aizone.gui.entity.GraphicField;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: lxmikh@gmail.com
 */

public class LogicSystem {
    //Переменные игрока и компьютера
    //Кто победил? Пока false в обоих переменных - никто не победил
    public boolean playerWin;
    public boolean compWin;

    public static boolean playerWasShoot;
    public static boolean playerHit;

    public static final int PAUSE = 5;

    private int playerDeadShip;
    private int compDeadShip;

    public Field computerFieldArr;
    public Field playerFieldArr;

    private MainUI mainFrameSwing;

    public void runLogic() {
        //Инициализация игрового поля и расстановка кораблей
        pcFields();
        playerFieldArr.fieldGenerate();
        computerFieldArr.fieldGenerate();
    }

    public void startGame(){
        do {
            playerWasShoot = false;
            waitUserAttack();

            if (playerHit) {
                compDeadShip++;
                playerHit = false;
                playerWin = whoWin("computer");
                if (playerWin) {
                    continue;
                }
            }

            if(computerAttack()){
                playerDeadShip++;
                compWin = whoWin("player");
                continue;
            }

        } while(!playerWin);

        JOptionPane.showOptionDialog(null,
                "YOU WIN", "Message",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
    }

    public boolean whoWin(String type){
        int playerDeadShips = getPDS();
        int computerDeadShips = getCDS();

        if(type.equals("computer")){
            if(computerDeadShips == Ship.SUM_COUNT_CELL_W_SHIP){
                return true;
            }
        }else if(type.equals("player")){
            if(playerDeadShips == Ship.SUM_COUNT_CELL_W_SHIP){
                return true;
            }
        }
        return false;
    }

    private boolean computerAttack() {
        int x = Field.genRNDCoord();
        int y = Field.genRNDCoord();

        GraphicField playerSwingField = MainUI.getPlayerField();
        GraphicCell gCells = playerSwingField.getSwingCells()[x][y];

        Cell[][] cells = playerFieldArr.getLogicField();
        Cell cell = cells[x][y];

        while (cell.isWasFired()) {
            x = Field.genRNDCoord();
            y = Field.genRNDCoord();
            cell = cells[x][y];
        }

        if (cell.isShip()) {
            gCells.setShip();
        }
        cell.setWasFired(true);
        gCells.setAttacked();
        gCells.repaint();

        return cell.isShip();
    }

    public static void waitUserAttack() {
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(PAUSE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!playerWasShoot);
    }

    public void pcFields() {
        playerFieldArr = new Field();
        computerFieldArr = new Field();
    }

    public Field getComputerField(){
        return computerFieldArr;
    }

    public Field getPlayerField(){
        return playerFieldArr;
    }

    public void setMainFrameSwing(MainUI mainFrameSwing) {
        this.mainFrameSwing = mainFrameSwing;
    }

    public int getPDS(){
        return playerDeadShip;
    }

    public int getCDS(){
        return compDeadShip;
    }
}
