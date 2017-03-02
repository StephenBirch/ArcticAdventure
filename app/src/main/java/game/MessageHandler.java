package game;

import android.os.Handler;
import android.os.Message;

/**
 * @author Andrew
 * 
 *         Thread has to communicate ASyncronously, so a Handler is used to pass
 *         messages through, this Class is where the messages are captured
 */

public class MessageHandler {
	GameActivity main;
	Handler mHandler;

	public MessageHandler(GameActivity m) {
		main = m;
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message m) {
				if (m.getData().getBoolean(GameActivity.levelupPopup)) {
					main.level.levelUpPopup();
					return;
				} else if (m.getData().getBoolean(GameActivity.lives)) {
					main.displayLives();
					return;
				} else if (m.getData().getBoolean("time")) {
					// main.tutorialPopup();
					return;
				} else if (m.getData().getBoolean("hidetutorial")) {
					if (main.gameState == GameActivity.GAME_PLAYING) {
						if (main.tutorialPopup != null) {
							if (main.tutorialPopup.isShowing()) {
								main.tutorialPopup.dismiss();
							}
						}
					}
					return;
				}
			}
		};
	}
}
