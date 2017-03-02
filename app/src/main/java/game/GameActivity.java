package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import mapitems.APlayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.flygames.arcticadventure.R;

/**
 * @author Andrew
 *
 *         Main actual game class. This has references all the different Classes
 *
 */
public class GameActivity extends Activity {
	public GestureHandler gestures;
	public MyView view;
	public AMap map;
	public MyThread thread;
	public APlayer player;
	public MyLevelController level;
	public MessageHandler message;
	public GameTimer timer;

	public int playerLives = 5;
	public int currentLevel = 1;
	public int maxLevel = 0;

	public static final int NEXTLEVEL = 1111;
	public static final int RESETLEVEL = 2222;
	public static final int PREVIOUSLEVEL = 3333;
	public static final int LEVELSELECT = 0;

	public LinearLayout layout;
	public PopupWindow tutorialPopup;

	public ImageView lifeOne;
	public ImageView lifeTwo;
	public ImageView lifeThree;
	public ImageView lifeFour;
	public ImageView lifeFive;
	public ImageView[] lifeArray = new ImageView[5];

	public int gameState = 2;
	public static final int GAME_PLAYING = 1;
	public static final int GAME_STOPPED = 2;
	public static final int MAX_LEVELS = 15;

	public static final String levelupPopup = "levelupPopup";
	public static final String lives = "lives";
	public static final String LEVEL_FILENAME = "levelfile";

	public boolean singleLevel = false;

	public TextView timerTextView;

	public HashMap<Integer,Integer> times = new HashMap();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamescreen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		gestures = new GestureHandler(this);
		view = (MyView) findViewById(R.id.view);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int y = displaymetrics.heightPixels;
		int x = displaymetrics.widthPixels;
		view.setUp(x, y, this);
		message = new MessageHandler(this);
		level = new MyLevelController(this);

		lifeOne = (ImageView) findViewById(R.id.life1);
		lifeArray[0] = lifeOne;
		lifeTwo = (ImageView) findViewById(R.id.life2);
		lifeArray[1] = lifeTwo;
		lifeThree = (ImageView) findViewById(R.id.life3);
		lifeArray[2] = lifeThree;
		lifeFour = (ImageView) findViewById(R.id.life4);
		lifeArray[3] = lifeFour;
		lifeFive = (ImageView) findViewById(R.id.life5);
		lifeArray[4] = lifeFive;

		readFile();

		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("single")) {
				// Start single level
				singleLevel = true;
				currentLevel = getIntent().getExtras().getInt("single");
				level.newLevel(currentLevel);
				Log.e("Single level", ""+currentLevel);
			 } else {
				// Continous play from current level
				level.newLevel(currentLevel);
				Log.e("Continuous", ""+currentLevel);
			}
		} else {
			// If we're here it must be the first time we've played
			level.newLevel(currentLevel);
			Log.e("AAAHHH","PANIC");
		}

		// TEMP TESTING CODE
		// TODO: REMOVE
		Button b1 = (Button) findViewById(R.id.skipbutton);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				level.nextLevel();
			}
		});
		timerTextView = (TextView) findViewById(R.id.timer);
		timer = new GameTimer(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, NEXTLEVEL, 0, R.string.NextLevel);
		menu.add(0, RESETLEVEL, 0, R.string.ResetLevel);
		menu.add(0, PREVIOUSLEVEL, 0, R.string.PreviousLevel);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case NEXTLEVEL:
			sendMessage(levelupPopup);
			return true;
		case RESETLEVEL:
			level.newLevel(currentLevel);
			playerLives = 5;
			return true;
		case PREVIOUSLEVEL:
			currentLevel--;
			level.newLevel(currentLevel);
			return true;
		}
		return false;
	}

	public boolean onTouchEvent(MotionEvent event) {
		return gestures.getGestureScanner().onTouchEvent(event);
	}

	public void lifeLost() {
		for (int y = 0; y < map.gridSizeY; y++) {
			for (int x = 0; x < map.gridSizeX; x++) {
				if (map.grid[x][y] != null) {
					if (map.grid[x][y].getClass().equals(APlayer.class)) {
						map.grid[x][y] = null;
					}
				}
			}
		}

		if (playerLives > 1) {
			playerLives--;
			sendMessage(lives);
			Message msg = message.mHandler.obtainMessage();
			Bundle b = new Bundle();
			b.putBoolean("lives", true);
			msg.setData(b);
			message.mHandler.sendMessage(msg);
			player = new APlayer(this, map.respawnY, map.respawnX);
			map.grid[map.respawnY][map.respawnX] = player;
		} else {
			//TODO Loose popup
		}
	}

	public void saveFile() {
		Log.e("Inside", "SaveFile");
		try {
			Context con = this;
			File file = new File(con.getFilesDir(), GameActivity.LEVEL_FILENAME);

			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fop = con.openFileOutput(GameActivity.LEVEL_FILENAME,
					Context.MODE_PRIVATE);
			try {
				fop.write(("*" + maxLevel).getBytes());
				for (int i = 1; i < 16; i++ ) {
					if (times.containsKey(i)) {
						fop.write(("\n" + times.get(i)).getBytes());
						Log.e("SAVING!!",""+times.get(i));
					} else {
						fop.write("\n-".getBytes());
					}
				}
				fop.close();
			} catch (IOException e) {
				Log.w("output",
						e.getMessage() + e.getLocalizedMessage() + e.getCause());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFile() {
		File file = new File(getFilesDir(), GameActivity.LEVEL_FILENAME);
		ArrayList<String> readLines = new ArrayList<String>();

		int size = 0;
		if (file.exists()) {
			size = (int) file.length();
		}

		if (size != 0) {
			InputStream is = null;
			try {
				is = openFileInput(GameActivity.LEVEL_FILENAME);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			String out;
			try {
				while ((out = reader.readLine()) != null) {
					readLines.add(out);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (readLines.get(0) != null) {
				currentLevel = maxLevel = Integer.parseInt(readLines.get(0).substring(1));
				Toast.makeText(this, "Loaded max level of: " + Integer.parseInt(readLines.get(0).substring(1)), Toast.LENGTH_SHORT).show();
				for (int i = 1 ; i < readLines.size() ; i++ ) {
					String line = readLines.get(i);
					if (line.equals("-")) {
						break;
					}
					times.put(i, Integer.parseInt(line));
				}
			} else {
				currentLevel = maxLevel = 1;
			}
		} else {
			currentLevel = maxLevel = 1;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if ((requestCode == LEVELSELECT) && (data != null)) {
			if (data.getExtras().containsKey("level")) {
				if (maxLevel < data.getExtras().getInt("level")) {
					currentLevel = data.getExtras().getInt("level");
					level.newLevel(currentLevel);
				} else {
					finish();
				}
			}
		}
	}

	public void displayLives() {
		for (int i = 0; i < 5; i++) {
			if (playerLives > i) {
				lifeArray[i].setImageResource(R.drawable.eskihappy);
			} else {
				lifeArray[i].setImageResource(R.drawable.eskisad);
			}
		}
	}

	public void sendMessage(String toSend) {
		Message msg = message.mHandler.obtainMessage();
		Bundle b = new Bundle();
		b.putBoolean(toSend, true);
		msg.setData(b);
		message.mHandler.sendMessage(msg);
	}

	@Override
	public void onDestroy() {
		gameState = GameActivity.GAME_STOPPED;
		if (maxLevel == 1) {
			System.exit(0);
		}
		super.onDestroy();
	}
}