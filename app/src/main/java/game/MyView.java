package game;

import mapitems.AForceEast;
import mapitems.AForceNorth;
import mapitems.AForceSouth;
import mapitems.AForceWest;
import mapitems.AGoal;
import mapitems.AHole;
import mapitems.AObject;
import mapitems.APenguin;
import mapitems.APlayer;
import mapitems.AShark;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author Andrew
 *         <p/>
 *         This class is handles everything to do with displaying things to the
 *         screen
 */
public class MyView extends View {
    GameActivity main;

    float width;
    float height;

    Paint paint;

    public boolean setUp = false;

    public float widthFraction;
    public float heightFraction;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setUp(int x, int y, GameActivity m) {
        setUp = true;
        main = m;
        width = x;
        height = y;
        height *= 0.9f;
        widthFraction = width / 10f;
        heightFraction = height / 13f;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if ((setUp) && (main.gameState == GameActivity.GAME_PLAYING)) {
            for (int y = 0; y < 13; y++) {
                for (int x = 0; x < 10; x++) {
                    if (main.map.grid[x][y] == null) {
                    } else if (main.map.grid[x][y].getClass().equals(
                            AObject.class)) {
                        canvas.drawBitmap(LevelSelectActivity.object, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            AGoal.class)) {
                        canvas.drawBitmap(LevelSelectActivity.goal, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            AShark.class)) {
                        AShark tempShark = (AShark) main.map.grid[x][y];
                        canvas.drawBitmap(LevelSelectActivity.bat, x
                                * (widthFraction)
                                + (tempShark.x * (widthFraction
                                * tempShark.movementCount / 10)), y
                                * (heightFraction)
                                + (tempShark.y * (heightFraction
                                * tempShark.movementCount / 10)), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            APenguin.class)) {
                        APenguin tempGuin = (APenguin) main.map.grid[x][y];
                        if (tempGuin.moveLeftNext) {
                            canvas.drawBitmap(
                                    LevelSelectActivity.projectile,
                                    x
                                            * (widthFraction)
                                            - (widthFraction
                                            * tempGuin.movementCount / 10),
                                    y * (heightFraction), paint);
                        } else {
                            canvas.drawBitmap(
                                    LevelSelectActivity.projectile,
                                    x
                                            * (widthFraction)
                                            + (widthFraction
                                            * tempGuin.movementCount / 10),
                                    y * (heightFraction), paint);
                        }

                    } else if (main.map.grid[x][y].getClass().equals(
                            AForceNorth.class)) {
                        canvas.drawBitmap(LevelSelectActivity.fnorth, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            AForceEast.class)) {
                        canvas.drawBitmap(LevelSelectActivity.feast, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            AForceWest.class)) {
                        canvas.drawBitmap(LevelSelectActivity.fwest, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            AForceSouth.class)) {
                        canvas.drawBitmap(LevelSelectActivity.fsouth, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            AHole.class)) {
                        canvas.drawBitmap(LevelSelectActivity.hole, x * (widthFraction), y
                                * (heightFraction), paint);
                    } else if (main.map.grid[x][y].getClass().equals(
                            APlayer.class)) {
                        switch (main.player.animationState) {
                            case 0:
                                canvas.drawBitmap(LevelSelectActivity.charDown, x * (widthFraction), y
                                        * (heightFraction), paint);
                                if (main.player.playerRunning) {
                                    main.player.animationState++;
                                }
                                break;
                            case 1:
                                canvas.drawBitmap(LevelSelectActivity.charLeft, x * (widthFraction), y
                                        * (heightFraction), paint);
                                if (main.player.playerRunning) {
                                    main.player.animationState++;
                                }
                                break;
                            case 2:
                                canvas.drawBitmap(LevelSelectActivity.charUp, x * (widthFraction), y
                                        * (heightFraction), paint);
                                if (main.player.playerRunning) {
                                    main.player.animationState++;
                                }
                                break;
                            case 3:
                                canvas.drawBitmap(LevelSelectActivity.charRight, x * (widthFraction), y
                                        * (heightFraction), paint);
                                if (main.player.playerRunning) {
                                    main.player.animationState = 0;
                                }
                                break;
                            default:
                                canvas.drawBitmap(LevelSelectActivity.player, x * (widthFraction), y
                                        * (heightFraction), paint);
                                break;
                        }
                    } else {
                        canvas.drawBitmap(LevelSelectActivity.ice, x * (widthFraction), y
                                * (heightFraction), paint);
                    }
                }
            }
        }
    }
}
