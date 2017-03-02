package game;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Andrew on 22/05/2016.
 */
public class GameTimer extends Thread {
    GameActivity main;

    public float millis = 0;
    public int tenths = 0;
    public int seconds = 0;
    public long startTime = System.currentTimeMillis();
    public boolean running = true;

    public GameTimer(GameActivity m) {
        main = m;
        this.start();
    }

    @Override
    public void run() {
        while (running) {
            try {
                millis = System.currentTimeMillis() - startTime;
                tenths = (int) (millis) % 100;
                seconds = (int) (millis / 1000);

                main.timerTextView.setText(String.format("%d:%02d", seconds, tenths));
                sleep(5);
            } catch (Exception e) {
                Log.e("Error"+e, "Ruh-Roh!");
            }
        }
    }

    public void resetTimer() {
        millis = 0;
        tenths = 0;
        seconds = 0;
        startTime = System.currentTimeMillis();
        Log.e("TIMER", "RESET CALLED");
    }
}
