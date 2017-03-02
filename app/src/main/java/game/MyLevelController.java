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
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.flygames.arcticadventure.R;

import java.util.ArrayList;

/**
 * @author Andrew This Class is used to set up the new levels once the current
 *         level is finished. It creates a new version of AMap, populates it and
 *         resumes the game.
 */
public class MyLevelController {
	public GameActivity main;
	public ArrayList<APenguin> penguins = new ArrayList<APenguin>();
	public ArrayList<AShark> sharks = new ArrayList<AShark>();
	public AShark tempShark;
	public APenguin tempPenguin;
	public int currentLevels = 15;

	public MyLevelController(GameActivity m) {
		main = m;
	}

	public void levelUpPopup() {

		main.gameState = GameActivity.GAME_STOPPED;
		LayoutInflater layoutInflater = (LayoutInflater) main.getBaseContext()
				.getSystemService(GameActivity.LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.popup, null);
		final PopupWindow popupWindow = new PopupWindow(popupView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView tv = (TextView) popupView.findViewById(R.id.tV3);
		tv.setText("Congratulations on beating\n Level " + main.currentLevel);
		Button btnDismiss = (Button) popupView.findViewById(R.id.dismiss);
		btnDismiss.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				nextLevel();
				popupWindow.dismiss();
			}
		});
		popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
	}

	public void levelSetup() {
		main.gameState = GameActivity.GAME_PLAYING;
		if (main.tutorialPopup != null) {
			main.tutorialPopup.dismiss();
		}
		if (main.thread == null) {
			main.thread = new MyThread(main);
			main.thread.start();
		}
		main.gameState = GameActivity.GAME_PLAYING;
	}

	public void nextLevel() {
		// Check if we've beaten the level quicker, update times if so

		int oldTime = Integer.MAX_VALUE;
		int newTime = main.timer.seconds;

		if (main.times.containsKey(main.currentLevel)) {
			if (!(main.times.get(main.currentLevel).equals("-"))) {
				oldTime = Integer.parseInt(main.times.get(main.currentLevel).toString().replaceAll(":", ""));
			}
		}

		if ( newTime < oldTime ) {
			Log.e("Saving", "Putting entry for: "+main.currentLevel);
			main.times.put(main.currentLevel, main.timer.seconds);
		}

		main.currentLevel++;
		// Update maxlevel and save if we've beaten a new level
		if (main.currentLevel > main.maxLevel) {
			Log.e("LEVEL", "Updating max level to: "+main.maxLevel);
			main.maxLevel = main.currentLevel;
		}


		main.saveFile();

		// If single level, end early
		if (main.maxLevel > main.MAX_LEVELS) {
			Log.e("LEVEL", "Max level reached so return to level select");
			main.finish();
			return;
		}
        main.timer.resetTimer();
		newLevel(main.currentLevel);
	}

	public void newLevel(int currentLevel) {
		Toast.makeText(main, "Starting Level: "+ currentLevel, Toast.LENGTH_SHORT).show();
		Message msg = main.message.mHandler.obtainMessage();
		Bundle b = new Bundle();
		b.putBoolean("level", true);
		msg.setData(b);
		main.message.mHandler.sendMessage(msg);
		switch (currentLevel) {
		case 1:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 5, 5);
			main.player = new APlayer(main, 0, 12);
			main.map.grid[0][12] = main.player;
			main.map.grid[9][0] = new AGoal();
			main.map.grid[9][1] = new AObject();
			main.map.grid[9][4] = new AObject();
			main.map.grid[8][5] = new AObject();
			main.map.grid[8][9] = new AObject();
			main.map.grid[7][0] = new AObject();
			main.map.grid[7][2] = new AObject();
			main.map.grid[7][12] = new AObject();
			main.map.grid[6][5] = new AObject();
			main.map.grid[5][4] = new AObject();
			main.map.grid[5][10] = new AObject();
			main.map.grid[4][5] = new AObject();
			main.map.grid[4][6] = new AObject();
			main.map.grid[3][7] = new AObject();
			main.map.grid[3][11] = new AObject();
			main.map.grid[2][2] = new AObject();
			main.map.grid[2][9] = new AObject();
			main.map.grid[0][5] = new AObject();

			levelSetup();
			break;

		case 2:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 0, 5);
			main.player = new APlayer(main, 5, 0);
			main.map.grid[5][0] = main.player;
			main.map.grid[6][12] = new AGoal();

			main.map.grid[1][9] = new AObject();
			main.map.grid[2][5] = new AObject();
			main.map.grid[2][12] = new AObject();
			main.map.grid[3][8] = new AObject();
			main.map.grid[4][0] = new AObject();
			main.map.grid[5][6] = new AObject();
			main.map.grid[6][0] = new AObject();
			main.map.grid[7][10] = new AObject();
			main.map.grid[0][0] = new AHole();
			main.map.grid[3][0] = new AHole();
			main.map.grid[6][8] = new AHole();
			main.map.grid[9][5] = new AHole();
			main.map.grid[9][7] = new AHole();

			levelSetup();
			break;

		case 3:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 0, 9);
			main.player = new APlayer(main, 9, 0);
			main.map.grid[9][0] = main.player;
			main.map.grid[7][0] = new AGoal();

			main.map.grid[0][5] = new AObject();
			main.map.grid[0][8] = new AObject();
			main.map.grid[1][2] = new AObject();
			main.map.grid[3][12] = new AObject();
			main.map.grid[4][6] = new AObject();
			main.map.grid[6][12] = new AObject();
			main.map.grid[7][5] = new AObject();
			main.map.grid[1][12] = new AHole();
			main.map.grid[5][0] = new AHole();
			main.map.grid[8][0] = new AHole();
			main.map.grid[8][3] = new AForceNorth();
			main.map.grid[5][9] = new AForceWest();

			levelSetup();
			break;

		case 4:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 7, 4);
			main.player = new APlayer(main, 4, 7);
			main.map.grid[4][7] = main.player;
			main.map.grid[3][5] = new AGoal();

			main.map.grid[0][4] = new AObject();
			main.map.grid[1][6] = new AObject();
			main.map.grid[3][0] = new AObject();
			main.map.grid[4][5] = new AObject();
			main.map.grid[4][10] = new AObject();
			main.map.grid[8][9] = new AObject();
			main.map.grid[0][5] = new AHole();
			main.map.grid[0][12] = new AHole();
			main.map.grid[9][6] = new AHole();
			main.map.grid[7][12] = new AForceWest();

			levelSetup();
			break;

		case 5:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 6, 9);
			main.player = new APlayer(main, 9, 6);
			main.map.grid[9][6] = main.player;
			main.map.grid[9][10] = new AGoal();

			main.map.grid[0][11] = new AObject();
			main.map.grid[1][6] = new AObject();
			main.map.grid[2][0] = new AObject();
			main.map.grid[1][12] = new AObject();
			main.map.grid[2][12] = new AObject();
			main.map.grid[3][2] = new AObject();
			main.map.grid[9][7] = new AHole();
			main.map.grid[9][11] = new AHole();
			main.map.grid[6][0] = new AHole();
			// main.map.grid[9][11] = new AForceWest();
			tempShark = new AShark(main, 7, 3);
			main.map.grid[7][3] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 3, 6);
			main.map.grid[3][6] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 6, 11);
			main.map.grid[6][11] = tempShark;
			sharks.add(tempShark);

			levelSetup();
			break;

		case 6:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 0, 5);
			main.player = new APlayer(main, 5, 0);
			main.map.grid[5][0] = main.player;
			main.map.grid[0][4] = new AGoal();

			main.map.grid[4][0] = new AObject();
			main.map.grid[5][6] = new AObject();
			main.map.grid[6][0] = new AObject();
			main.map.grid[0][5] = new AHole();
			main.map.grid[4][12] = new AHole();
			main.map.grid[9][6] = new AForceNorth();
			main.map.grid[3][12] = new AForceNorth();
			tempShark = new AShark(main, 1, 10);
			main.map.grid[1][10] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 4, 4);
			main.map.grid[4][4] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 6, 10);
			main.map.grid[6][10] = tempShark;
			sharks.add(tempShark);

			levelSetup();
			break;

		case 7:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 10, 3);
			main.player = new APlayer(main, 3, 10);
			main.map.grid[3][10] = main.player;
			main.map.grid[3][12] = new AGoal();

			main.map.grid[0][0] = new AObject();
			main.map.grid[0][6] = new AObject();
			main.map.grid[1][0] = new AObject();
			main.map.grid[1][5] = new AObject();
			main.map.grid[1][7] = new AObject();
			main.map.grid[2][4] = new AObject();
			main.map.grid[2][10] = new AObject();
			main.map.grid[3][11] = new AObject();
			main.map.grid[4][10] = new AObject();
			main.map.grid[6][5] = new AObject();
			main.map.grid[6][6] = new AObject();
			main.map.grid[6][7] = new AObject();
			main.map.grid[8][0] = new AObject();
			main.map.grid[0][9] = new AHole();
			main.map.grid[9][12] = new AHole();
			main.map.grid[9][10] = new AForceWest();
			tempPenguin = new APenguin(main, 1, 6);
			main.map.grid[1][6] = tempPenguin;
			penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 2, 7);
			main.map.grid[2][7] = tempPenguin;
			penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 7, 6);
			main.map.grid[7][6] = tempPenguin;
			penguins.add(tempPenguin);

			levelSetup();
			break;

		case 8:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 8, 8);
			main.player = new APlayer(main, 8, 8);
			main.map.grid[8][8] = main.player;
			main.map.grid[1][5] = new AGoal();

			main.map.grid[4][9] = new AObject();
			main.map.grid[4][12] = new AObject();
			main.map.grid[7][0] = new AObject();
			main.map.grid[8][9] = new AObject();
			main.map.grid[3][12] = new AHole();
			main.map.grid[6][4] = new AForceWest();
			main.map.grid[2][0] = new AForceSouth();
			main.map.grid[6][0] = new AForceSouth();
			tempPenguin = new APenguin(main, 0, 1);
			main.map.grid[0][1] = tempPenguin;
			penguins.add(tempPenguin);
			tempShark = new AShark(main, 0, 6);
			main.map.grid[0][6] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 3, 10);
			main.map.grid[3][10] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 5);
			main.map.grid[7][5] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 12);
			main.map.grid[7][12] = tempShark;
			sharks.add(tempShark);

			levelSetup();
			break;

		case 9:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 6, 4);
			main.player = new APlayer(main, 4, 6);
			main.map.grid[4][6] = main.player;
			main.map.grid[9][4] = new AGoal();

			main.map.grid[2][0] = new AObject();
			main.map.grid[3][9] = new AObject();
			main.map.grid[5][2] = new AObject();
			main.map.grid[8][10] = new AObject();
			main.map.grid[9][5] = new AObject();
			main.map.grid[1][11] = new AHole();
			main.map.grid[4][12] = new AForceWest();
			main.map.grid[9][12] = new AForceWest();
			tempPenguin = new APenguin(main, 3, 0);
			main.map.grid[3][0] = tempPenguin;
			penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 0, 8);
			main.map.grid[0][8] = tempPenguin;
			penguins.add(tempPenguin);
			tempShark = new AShark(main, 0, 5);
			main.map.grid[0][5] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 3, 7);
			main.map.grid[3][7] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 3);
			main.map.grid[7][3] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 5, 11);
			main.map.grid[5][11] = tempShark;
			sharks.add(tempShark);

			levelSetup();
			break;
		case 10:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 9, 0);
			main.player = new APlayer(main, 0, 9);
			main.map.grid[0][9] = main.player;
			main.map.grid[5][9] = new AObject();
			main.map.grid[4][4] = new AObject();
			main.map.grid[1][3] = new AObject();
			main.map.grid[6][3] = new AObject();
			main.map.grid[7][5] = new AObject();
			main.map.grid[9][1] = new AObject();
			main.map.grid[4][0] = new AObject();
			main.map.grid[9][0] = new AGoal();
			tempShark = new AShark(main, 2, 7);
			main.map.grid[2][7] = tempShark;
			sharks.add(tempShark);
			tempPenguin = new APenguin(main, 0, 0);
			main.map.grid[0][0] = tempPenguin;
			penguins.add(tempPenguin);

			levelSetup();
			break;

		case 16:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 0, 1);
			main.player = new APlayer(main, 1, 0);
			main.map.grid[1][0] = main.player;
			main.map.grid[0][5] = new AObject();
			main.map.grid[2][4] = new AObject();
			main.map.grid[4][7] = new AObject();
			main.map.grid[6][3] = new AObject();
			main.map.grid[5][11] = new AObject();
			main.map.grid[9][12] = new AObject();
			main.map.grid[7][8] = new AObject();
			main.map.grid[8][1] = new AObject();
			main.map.grid[9][12] = new AObject();
			main.map.grid[9][4] = new AObject();
			main.map.grid[8][8] = new AGoal();
			tempShark = new AShark(main, 7, 2);
			main.map.grid[7][2] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 0, 11);
			main.map.grid[0][11] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 4, 12);
			main.map.grid[4][12] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 11);
			main.map.grid[7][11] = tempShark;
			sharks.add(tempShark);
			tempPenguin = new APenguin(main, 0, 7);
			main.map.grid[0][7] = tempPenguin;
			penguins.add(tempPenguin);
			main.map.grid[9][6] = new AHole();
			main.map.grid[8][7] = new AHole();
			main.map.grid[6][7] = new AHole();

			levelSetup();
			break;

		case 12:
			penguins.clear();
			sharks.clear();
			main.map = new AMap(10, 13, 5, 0);
			main.gameState = GameActivity.GAME_PLAYING;
			main.player = new APlayer(main, 0, 5);
			main.map.grid[0][5] = main.player;
			main.map.grid[6][0] = new AGoal();
			main.map.grid[3][5] = new AForceNorth();
			main.map.grid[4][6] = new AForceNorth();
			main.map.grid[4][9] = new AForceNorth();
			main.map.grid[5][9] = new AForceNorth();
			main.map.grid[8][11] = new AForceNorth();
			main.map.grid[0][1] = new AForceSouth();
			main.map.grid[3][6] = new AForceSouth();
			main.map.grid[5][3] = new AForceSouth();
			main.map.grid[0][11] = new AForceEast();
			main.map.grid[1][10] = new AForceEast();
			main.map.grid[2][2] = new AForceEast();
			main.map.grid[7][8] = new AForceEast();
			main.map.grid[3][12] = new AForceWest();
			main.map.grid[4][0] = new AForceWest();
			main.map.grid[9][9] = new AForceWest();

			levelSetup();
			break;

		case 11:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 12, 0);
			main.player = new APlayer(main, 0, 12);
			main.map.grid[0][12] = main.player;
			main.map.grid[2][1] = new AGoal();
			main.map.grid[0][0] = new AObject();
			main.map.grid[0][2] = new AObject();
			main.map.grid[0][6] = new AObject();
			main.map.grid[1][5] = new AObject();
			main.map.grid[3][6] = new AObject();
			main.map.grid[4][1] = new AObject();
			main.map.grid[5][0] = new AObject();
			main.map.grid[5][3] = new AObject();
			main.map.grid[5][6] = new AObject();
			main.map.grid[6][1] = new AObject();
			main.map.grid[6][7] = new AObject();
			main.map.grid[7][0] = new AObject();
			main.map.grid[7][6] = new AObject();
			main.map.grid[8][2] = new AObject();
			main.map.grid[4][10] = new AObject();
			main.map.grid[4][12] = new AObject();
			main.map.grid[8][10] = new AObject();
			main.map.grid[8][12] = new AObject();
			tempShark = new AShark(main, 1, 12);
			main.map.grid[1][12] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 2, 5);
			main.map.grid[2][5] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 3);
			main.map.grid[7][3] = tempShark;
			sharks.add(tempShark);
			tempPenguin = new APenguin(main, 0, 8);
			main.map.grid[0][8] = tempPenguin;
			penguins.add(tempPenguin);

			levelSetup();
			break;

		case 13:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 12, 0);
			main.player = new APlayer(main, 0, 12);
			main.map.grid[0][12] = main.player;
			main.map.grid[9][0] = new AGoal();

			main.map.grid[1][1] = new AObject();
			main.map.grid[2][1] = new AObject();
			main.map.grid[1][5] = new AObject();
			main.map.grid[1][6] = new AObject();
			main.map.grid[1][7] = new AObject();
			main.map.grid[1][9] = new AObject();
			main.map.grid[2][6] = new AObject();
			main.map.grid[0][10] = new AObject();
			main.map.grid[1][10] = new AObject();
			main.map.grid[2][10] = new AObject();
			main.map.grid[3][10] = new AObject();
			main.map.grid[4][10] = new AObject();
			main.map.grid[3][12] = new AObject();
			main.map.grid[4][2] = new AObject();
			main.map.grid[5][4] = new AObject();
			main.map.grid[6][0] = new AObject();
			main.map.grid[6][11] = new AObject();
			main.map.grid[7][6] = new AObject();
			main.map.grid[8][8] = new AObject();
			main.map.grid[7][10] = new AObject();
			main.map.grid[8][12] = new AObject();
			main.map.grid[9][4] = new AObject();
			main.map.grid[6][7] = new AObject();
			main.map.grid[6][8] = new AObject();
			main.map.grid[6][9] = new AObject();
			tempShark = new AShark(main, 0, 4);
			main.map.grid[0][4] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 2, 5);
			main.map.grid[2][5] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 2);
			main.map.grid[7][2] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 9);
			main.map.grid[7][9] = tempShark;
			sharks.add(tempShark);
			tempPenguin = new APenguin(main, 3, 6);
			main.map.grid[3][6] = tempPenguin;
			penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 2, 7);
			main.map.grid[2][7] = tempPenguin;
			penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 0, 8);
			main.map.grid[0][8] = tempPenguin;
			penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 2, 9);
			main.map.grid[2][9] = tempPenguin;
			penguins.add(tempPenguin);

			levelSetup();
			break;

		case 14:

			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 5, 4);
			main.player = new APlayer(main, 4, 5);
			main.map.grid[4][5] = main.player;
			main.map.grid[6][6] = new AGoal();
			main.map.grid[0][4] = new AObject();
			main.map.grid[1][4] = new AObject();
			main.map.grid[0][9] = new AObject();
			main.map.grid[0][12] = new AObject();
			main.map.grid[2][11] = new AObject();
			main.map.grid[3][11] = new AObject();
			main.map.grid[3][12] = new AObject();
			main.map.grid[6][10] = new AObject();
			main.map.grid[7][3] = new AObject();
			main.map.grid[7][8] = new AObject();
			main.map.grid[8][11] = new AObject();
			main.map.grid[9][9] = new AObject();
			tempShark = new AShark(main, 0, 3);
			main.map.grid[0][3] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 3, 6);
			main.map.grid[3][6] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 7, 2);
			main.map.grid[7][2] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 1, 10);
			main.map.grid[1][10] = tempShark;
			sharks.add(tempShark);
			tempShark = new AShark(main, 5, 2);
			main.map.grid[5][2] = tempShark;
			sharks.add(tempShark);
			// tempPenguin = new APenguin(main, 5, 2); main.map.grid[5][2] =
			// tempPenguin;penguins.add(tempPenguin);
			tempPenguin = new APenguin(main, 0, 7);
			main.map.grid[0][7] = tempPenguin;
			penguins.add(tempPenguin);
			main.map.grid[4][2] = new AHole();
			main.map.grid[1][5] = new AHole();
			main.map.grid[7][5] = new AHole();

			levelSetup();
			break;

		case 15:
			penguins.clear();
			sharks.clear();
			main.gameState = GameActivity.GAME_PLAYING;
			main.map = new AMap(10, 13, 5, 5);
			main.player = new APlayer(main, 5, 5);
			main.map.grid[5][5] = main.player;
			main.map.grid[9][0] = new AGoal();
			main.map.grid[0][5] = new AObject();
			main.map.grid[1][5] = new AObject();
			main.map.grid[3][3] = new AObject();
			main.map.grid[4][0] = new AObject();
			main.map.grid[7][3] = new AObject();
			main.map.grid[9][1] = new AObject();
			main.map.grid[9][8] = new AObject();
			main.map.grid[7][0] = new AHole();
			main.map.grid[3][8] = new AHole();
			main.map.grid[9][9] = new AHole();
			main.map.grid[0][7] = new AForceSouth();
			main.map.grid[5][10] = new AForceEast();
			main.map.grid[9][12] = new AForceNorth();
			Toast.makeText(main, "Level 15", Toast.LENGTH_SHORT)
					.show();
			levelSetup();
			break;

		default:
			// No more levels, go back to loading screen
			// Hopefully unreachable code, toasting to warn
			Toast.makeText(main, "Error, in default case in mylevelcontroller", Toast.LENGTH_SHORT)
					.show();
			main.finish();
			break;
		}
	}
}
