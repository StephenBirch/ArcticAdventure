package mapitems;

import android.widget.ShareActionProvider;

import game.GameActivity;
import game.AMap.Direction;

public class APlayer extends AMoveable {

    public boolean playerRunning = false;
    Direction direction;
    public int animationState = 0;

    public APlayer(GameActivity m, int x, int y) {
        super(m);
        locationX = x;
        locationY = y;
    }

    public void toMove(Direction d) {
        if (!(playerRunning)) {
            direction = d;
            playerRunning = true;
        }
    }

    @Override
    public void move() {
        if (playerRunning) {
            move(direction);
        }
    }

    public void move(Direction d) {
        switch (d) {
            case NORTH:
                if (locationY > 0) {
                    if (main.map.grid[locationX][locationY - 1] == null) {
                        moveNorth();
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AGoal.class)) {
                        playerRunning = false;
                        main.sendMessage(GameActivity.levelupPopup);
                        break;
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AShark.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(APenguin.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AHole.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AObject.class)) {
                        playerRunning = false;
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AForceEast.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.EAST);
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AForceSouth.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.SOUTH);
                    } else if (main.map.grid[locationX][locationY - 1].getClass()
                            .equals(AForceWest.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.WEST);
                    } else {
                        playerRunning = false;
                    }
                } else {
                    playerRunning = false;
                }
                break;
            case EAST:
                if (locationX < main.map.gridSizeX - 1) {
                    if (main.map.grid[locationX + 1][locationY] == null) {
                        moveEast();
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AGoal.class)) {
                        playerRunning = false;
                        main.sendMessage(GameActivity.levelupPopup);
                        break;
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AShark.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AForceNorth.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.NORTH);
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AForceSouth.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.SOUTH);
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AForceWest.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.WEST);
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(APenguin.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AHole.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX + 1][locationY].getClass()
                            .equals(AObject.class)) {
                        playerRunning = false;
                    } else {
                        playerRunning = false;
                    }
                } else {
                    playerRunning = false;
                }
                break;
            case SOUTH:
                if (locationY < main.map.gridSizeY - 1) {
                    if (main.map.grid[locationX][locationY + 1] == null) {
                        moveSouth();
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AGoal.class)) {
                        playerRunning = false;
                        main.sendMessage(GameActivity.levelupPopup);
                        break;
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AShark.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AForceNorth.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.NORTH);
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AForceEast.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.EAST);
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AForceWest.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.WEST);

                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(APenguin.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AHole.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX][locationY + 1].getClass()
                            .equals(AObject.class)) {
                        playerRunning = false;
                    } else {

                        playerRunning = false;
                    }
                } else {
                    playerRunning = false;
                }
                break;
            case WEST:
                if (locationX > 0) {
                    if (main.map.grid[locationX - 1][locationY] == null) {
                        moveWest();
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AGoal.class)) {
                        playerRunning = false;
                        main.sendMessage(GameActivity.levelupPopup);
                        break;
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AShark.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AForceNorth.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.NORTH);
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AForceEast.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.EAST);
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AForceSouth.class)) {
                        playerRunning = false;
                        main.player.toMove(Direction.SOUTH);
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(APenguin.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AHole.class)) {
                        playerRunning = false;
                        main.lifeLost();
                    } else if (main.map.grid[locationX - 1][locationY].getClass()
                            .equals(AObject.class)) {
                        playerRunning = false;
                    } else {
                        playerRunning = false;
                    }
                } else {
                    playerRunning = false;
                }
                break;
        }
    }
}