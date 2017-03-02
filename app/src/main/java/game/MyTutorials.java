package game;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.flygames.arcticadventure.R;

public class MyTutorials {
	public GameActivity main;
	
	public MyTutorials(GameActivity m) {
		main = m;
	}

	public void tutorialPopup() {
		LayoutInflater layoutInflater = (LayoutInflater) main.getBaseContext()
				.getSystemService(GameActivity.LAYOUT_INFLATER_SERVICE);
		final View popupView = layoutInflater.inflate(R.layout.tutorialpopup,
				null);
		main.tutorialPopup = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		TextView tv = (TextView) popupView.findViewById(R.id.tV5);
		switch (main.currentLevel) {
		case 1:
			tv.setText("Swipe to move and\n reach the goal!");
			main.tutorialPopup.showAtLocation(popupView, Gravity.TOP, 0, 0);
			break;
		case 2:
			tv.setText("Avoid the holes\n falling in them hurts!");
			main.tutorialPopup.showAtLocation(popupView, Gravity.CENTER, 0, 0);
			break;
		case 3:
			tv.setText("Some things on the map\n change Eski's direction");
			main.tutorialPopup.showAtLocation(popupView, Gravity.CENTER, 0, 0);
			break;
		case 5:
			tv.setText("Try and avoid the hungry sharks!");
			main.tutorialPopup.showAtLocation(popupView, Gravity.TOP, 0, 0);
			break;
		case 6:
			tv.setText("Try and Collect the fishys");
			main.tutorialPopup.showAtLocation(popupView, Gravity.TOP, 0, 0);
			break;
		case 8:
			tv.setText("Patches of dirt stop eski sliding\n but only once! ");
			main.tutorialPopup.showAtLocation(popupView, Gravity.TOP, 0, 0);
			break;

		}
		Thread t = new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = main.message.mHandler.obtainMessage();
				Bundle b = new Bundle();
				b.putBoolean("hidetutorial", true);
				msg.setData(b);
				main.message.mHandler.sendMessage(msg);
			}
		};
		t.start();
	}
}
