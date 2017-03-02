package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.flygames.arcticadventure.R;

public class LevelSelectActivity extends Activity {
	public ImageButton b1;
	public ImageButton b2;
	public ImageButton b3;
	public ImageButton b4;
	public ImageButton b5;
	public ImageButton b6;
	public ImageButton b7;
	public ImageButton b8;
	public ImageButton b9;
	public ImageButton b10;
	public ImageButton b11;
	public ImageButton b12;
	public ImageButton b13;
	public ImageButton b14;
	public ImageButton b15;
	public ImageButton b16;

	public ImageView t1;
	public ImageView t2;
	public ImageView t3;
	public ImageView t4;
	public ImageView t5;
	public ImageView t6;
	public ImageView t7;
	public ImageView t8;
	public ImageView t9;
	public ImageView t10;
	public ImageView t11;
	public ImageView t12;
	public ImageView t13;
	public ImageView t14;
	public ImageView t15;

	public static Bitmap ice;
	public static Bitmap player;
	public static Bitmap object;
	public static Bitmap goal;
	public static Bitmap winScreen;
	public static Bitmap bat;
	public static Bitmap projectile;
	public static Bitmap hole;
	public static Bitmap fnorth;
	public static Bitmap feast;
	public static Bitmap fwest;
	public static Bitmap fsouth;
	public static Bitmap charDown;
	public static Bitmap charUp;
	public static Bitmap charLeft;
	public static Bitmap charRight;

	public float widthFraction;
	public float heightFraction;

	public Intent returnSet;
	public int maxLevel = 1;
	public ImageButton[] iButtons = new ImageButton[16];
	public ImageView[] iViews = new ImageView[16];

	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.levelselectionpercent);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		returnSet = new Intent();

		b1 = (ImageButton) findViewById(R.id.iB1);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(1);
			}
		});
		iButtons[0] = b1;
		b2 = (ImageButton) findViewById(R.id.iB2);
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(2);
			}
		});
		iButtons[1] = b2;
		b3 = (ImageButton) findViewById(R.id.iB3);
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(3);
			}
		});
		iButtons[2] = b3;
		b4 = (ImageButton) findViewById(R.id.iB4);
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(4);
			}
		});
		iButtons[3] = b4;
		b5 = (ImageButton) findViewById(R.id.iB5);
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(5);
			}
		});
		iButtons[4] = b5;
		b6 = (ImageButton) findViewById(R.id.iB6);
		b6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(6);
			}
		});
		iButtons[5] = b6;
		b7 = (ImageButton) findViewById(R.id.iB7);
		b7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(7);
			}
		});
		iButtons[6] = b7;
		b8 = (ImageButton) findViewById(R.id.iB8);
		b8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(8);
			}
		});
		iButtons[7] = b8;
		b9 = (ImageButton) findViewById(R.id.iB9);
		b9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(9);
			}
		});
		iButtons[8] = b9;
		b10 = (ImageButton) findViewById(R.id.iB10);
		b10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(10);
			}
		});
		iButtons[9] = b10;
		b11 = (ImageButton) findViewById(R.id.iB11);
		b11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(11);
			}
		});
		iButtons[10] = b11;
		b12 = (ImageButton) findViewById(R.id.iB12);
		b12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(12);
			}
		});
		iButtons[11] = b12;
		b13 = (ImageButton) findViewById(R.id.iB13);
		b13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(13);
			}
		});
		iButtons[12] = b13;
		b14 = (ImageButton) findViewById(R.id.iB14);
		b14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(14);
			}
		});
		iButtons[13] = b14;
		b15 = (ImageButton) findViewById(R.id.iB15);
		b15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startLevel(15);
			}
		});
		iButtons[14] = b15;


		t1 = (ImageView) findViewById(R.id.iV1);
		iViews[0] = t1;
		t2 = (ImageView) findViewById(R.id.iV2);
		iViews[1] = t2;
		t3 = (ImageView) findViewById(R.id.iV3);
		iViews[2] = t3;
		t4 = (ImageView) findViewById(R.id.iV4);
		iViews[3] = t4;
		t5 = (ImageView) findViewById(R.id.iV5);
		iViews[4] = t5;
		t6 = (ImageView) findViewById(R.id.iV6);
		iViews[5] = t6;
		t7 = (ImageView) findViewById(R.id.iV7);
		iViews[6] = t7;
		t8 = (ImageView) findViewById(R.id.iV8);
		iViews[7] = t8;
		t9 = (ImageView) findViewById(R.id.iV9);
		iViews[8] = t9;
		t10 = (ImageView) findViewById(R.id.iV10);
		iViews[9] = t10;
		t11 = (ImageView) findViewById(R.id.iV11);
		iViews[10] = t11;
		t12 = (ImageView) findViewById(R.id.iV12);
		iViews[11] = t12;
		t13 = (ImageView) findViewById(R.id.iV13);
		iViews[12] = t13;
		t14 = (ImageView) findViewById(R.id.iV14);
		iViews[13] = t14;
		t15 = (ImageView) findViewById(R.id.iV15);
		iViews[14] = t15;

		// TEST BITMAPS
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		float height = displaymetrics.heightPixels;
		float width = displaymetrics.widthPixels;
		height *= 0.9f;
		widthFraction = width / 10f;
		heightFraction = height / 13f;


		Bitmap tempPlayer = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		player = Bitmap.createScaledBitmap(tempPlayer, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempObject = BitmapFactory.decodeResource(getResources(),
				R.drawable.rock);
		object = Bitmap.createScaledBitmap(tempObject, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempGoal = BitmapFactory.decodeResource(getResources(),
				R.drawable.goal);
		goal = Bitmap.createScaledBitmap(tempGoal, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempBat = BitmapFactory.decodeResource(getResources(),
				R.drawable.shark);
		bat = Bitmap.createScaledBitmap(tempBat, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempProjectile = BitmapFactory.decodeResource(getResources(),
				R.drawable.projectile);
		projectile = Bitmap.createScaledBitmap(tempProjectile,
				(int) widthFraction, (int) heightFraction, true);
		winScreen = BitmapFactory
				.decodeResource(getResources(), R.drawable.win);
		Bitmap tempCharDown = BitmapFactory.decodeResource(getResources(),
				R.drawable.chardown);
		charDown = Bitmap.createScaledBitmap(tempCharDown, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempCharLeft = BitmapFactory.decodeResource(getResources(),
				R.drawable.charleft);
		charLeft = Bitmap.createScaledBitmap(tempCharLeft, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempCharRight = BitmapFactory.decodeResource(getResources(),
				R.drawable.charright);
		charRight = Bitmap.createScaledBitmap(tempCharRight,
				(int) widthFraction, (int) heightFraction, true);
		Bitmap tempCharUp = BitmapFactory.decodeResource(getResources(),
				R.drawable.charup);
		charUp = Bitmap.createScaledBitmap(tempCharUp, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempHole = BitmapFactory.decodeResource(getResources(),
				R.drawable.hole);
		hole = Bitmap.createScaledBitmap(tempHole, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempfnorth = BitmapFactory.decodeResource(getResources(),
				R.drawable.fnorth);
		fnorth = Bitmap.createScaledBitmap(tempfnorth, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempfeast = BitmapFactory.decodeResource(getResources(),
				R.drawable.feast);
		feast = Bitmap.createScaledBitmap(tempfeast, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempfwest = BitmapFactory.decodeResource(getResources(),
				R.drawable.fwest);
		fwest = Bitmap.createScaledBitmap(tempfwest, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempfsouth = BitmapFactory.decodeResource(getResources(),
				R.drawable.fsouth);
		fsouth = Bitmap.createScaledBitmap(tempfsouth, (int) widthFraction,
				(int) heightFraction, true);
		Bitmap tempice = BitmapFactory.decodeResource(getResources(),
				R.drawable.ice);
		ice = Bitmap.createScaledBitmap(tempice, (int) widthFraction,
				(int) heightFraction, true);

		readFile();
		if ( maxLevel == 1 ) {
			startLevel(1);
		}
		hideButtons();
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
					Log.e("File output", out);
				}
			} catch (IOException e) {
				Log.w("exception", "fuck");
			}
			if (readLines.get(0) != null) {
				maxLevel = Integer.parseInt(readLines.get(0).substring(1));
				for (int i = 1; i < readLines.size(); i++) {
					String line = readLines.get(i);
					if (line.equals("-")) {
						break;
					}
					if(Integer.parseInt(line) < 10) {
						iViews[i - 1].setImageDrawable(getDrawable(R.drawable.onestar));

					} else if (Integer.parseInt(line) < 20) {
						iViews[i - 1].setImageDrawable(getDrawable(R.drawable.onestar));

					} else {
						iViews[i - 1].setImageDrawable(getDrawable(R.drawable.onestar));
					}
				}
			} else {
				maxLevel = 1;
			}
		}
	}

	public void hideButtons() {
		for (int i = 0; i < 15; i++) {
			if (maxLevel > i) {
				iButtons[i].setVisibility(View.VISIBLE);
				iViews[i].setVisibility(View.VISIBLE);
			} else {
				iButtons[i].setVisibility(View.INVISIBLE);
				iViews[i].setVisibility(View.INVISIBLE);
			}
		}
	}

	public void startLevel(int level) {
		Log.e("Inside startLevel", ""+level);
		Log.e("Max level", ""+maxLevel);
		Intent i = new Intent(LevelSelectActivity.this, GameActivity.class);
		// TODO
		if ((maxLevel > level) || (maxLevel >= GameActivity.MAX_LEVELS)) {
			i.putExtra("single", level);
		}
		startActivity(i);
		hideButtons();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		readFile();
		hideButtons();
	}

	@Override
	public void onStart() {
		super.onStart();
		readFile();
		hideButtons();
	}
}
