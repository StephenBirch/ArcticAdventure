package game;

import mapitems.APenguin;
import mapitems.AShark;

public class MyThread extends Thread {
	public GameActivity main;
	public float millis = 0;
    public int tenths = 0;
    public int seconds = 0;
    public int minutes = 0;
    public long startTime = System.currentTimeMillis();

	public MyThread(GameActivity m) {
		main = m;
	}

	@Override
	public void run() {
		long lastTime = System.currentTimeMillis();
		while (true) {
			if (main.gameState == GameActivity.GAME_PLAYING) {
				long currentTime = System.currentTimeMillis();
				long deltaTime = currentTime - lastTime;

				if (deltaTime > 100) {
					main.player.move();

					for (AShark a : main.level.sharks) {
						a.movementCount++;
						if (a.movementCount % 10 == 0) {
							a.move();
							a.movementCount = 0;
						}
					}
					for (APenguin a : main.level.penguins) {
						a.movementCount++;
						if (a.movementCount % 10 == 0) {
							a.move();
							a.movementCount = 0;
						}
					}
					lastTime = currentTime;
					main.view.postInvalidate();
				}

			} else {
				lastTime = System.currentTimeMillis();
				//TODO: Pause timer
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
