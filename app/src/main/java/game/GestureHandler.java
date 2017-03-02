package game;

import game.AMap.Direction;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author Andrew
 * 
 *         This Class detects when the screen is swiped in a given direction
 *         (U/D/L/R) The distance and speed that is required to activate a swipe
 *         is held in SWIPE_MIN_DISTANCE and SWIPE_THRESHOLD_VELOCITY
 */
class GestureHandler implements OnTouchListener {
	public static final int SWIPE_MIN_DISTANCE = 20;
	public static final int SWIPE_THRESHOLD_VELOCITY = 50;
	public GestureDetector gestureScanner;
	public GameActivity main;

	public GestureHandler(Context context) {
		createGestureListener();
		main = (GameActivity) context;
	}

	protected void createGestureListener() {
		setGestureScanner(new GestureDetector(new OnGestureListener() {
			public boolean onScroll(MotionEvent event1, MotionEvent event2,
					float distanceX, float distanceY) {
				return true;
			}

			public boolean onDown(MotionEvent event) {
				return true;
			}

			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				if (Math.abs(e1.getY() - e2.getY()) > Math.abs(e1.getX()
						- e2.getX())) {
					if (Math.abs(velocityY) < SWIPE_THRESHOLD_VELOCITY) {
						return false;
					}
					if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE) {
						main.player.toMove(Direction.NORTH);
						// NORTH
					} else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE) {
						main.player.toMove(Direction.SOUTH);
						// SOUTH
					}
				} else {
					if (Math.abs(velocityX) < SWIPE_THRESHOLD_VELOCITY) {
						return false;
					}
					if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE) {
						main.player.toMove(Direction.WEST);
						// WEST
					} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE) {
						main.player.toMove(Direction.EAST);
						// EAST
					}
				}
				return true;
			}

			public void onLongPress(MotionEvent event) {
			}

			public void onShowPress(MotionEvent event) {
			}

			public boolean onSingleTapUp(MotionEvent event) {
				return true;
			}
		}));
	}

	public GestureDetector getGestureScanner() {
		return gestureScanner;
	}

	public void setGestureScanner(GestureDetector gestureScanner) {
		this.gestureScanner = gestureScanner;
	}

	 @Override
	 public boolean onTouch(View v, MotionEvent event) {
	 Log.e("Touch", event.getX() + "+" + event.getY());
	 return false;
	 }

}
