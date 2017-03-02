package mapitems;

import game.GameActivity;
import android.util.Log;


public abstract class AMoveable extends AMapItem {
	GameActivity main;
	
	public int locationX;
	public int locationY;
	
	public int movementCount = 0;

	public AMoveable(GameActivity m) {
		main = m;
	}

	public void moveWest() {
		if (locationX > 0) {
			if (main.map.grid[locationX - 1][locationY] == null) {
				main.map.grid[locationX - 1][locationY] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationX -= 1;
			} else if (main.map.grid[locationX - 1][locationX] == main.player) {
				main.lifeLost();
				main.map.grid[locationX - 1][locationY] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationX -= 1;
			} else {
				Log.e("Something can't move!", "Location:" + locationX + ","
						+ locationY);
			}
		}
	}

	public void moveSouth() {
		if (locationY < main.map.gridSizeY - 1) {
			if (main.map.grid[locationX][locationY + 1] == null) {
				main.map.grid[locationX][locationY + 1] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationY += 1;
			} else if (main.map.grid[locationX][locationY + 1] == main.player) {
				main.lifeLost();
				main.map.grid[locationX][locationY + 1] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationY += 1;
			} else {
				Log.e("Something can't move!", "Location:" + locationX + ","
						+ locationY);
			}
		}
	}

	public void moveEast() {
		if (locationX < main.map.gridSizeX - 1) {
			if (main.map.grid[locationX + 1][locationY] == null) {
				main.map.grid[locationX + 1][locationY] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationX += 1;
			} else if (main.map.grid[locationX + 1][locationY] == main.player) {
				main.lifeLost();
				main.map.grid[locationX + 1][locationY] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationX += 1;
			} else {
				Log.e("Something can't move!", "Location:" + locationX + ","
						+ locationY);
			}
		}
	}

	public void moveNorth() {
		if (locationY > 0) {
			if (main.map.grid[locationX][locationY - 1] == null) {
				main.map.grid[locationX][locationY - 1] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationY -= 1;
			} else if (main.map.grid[locationX][locationY - 1] == main.player) {
				main.lifeLost();
				main.map.grid[locationX][locationY - 1] = main.map.grid[locationX][locationY];
				main.map.grid[locationX][locationY] = null;
				locationY -= 1;
			} else {
				Log.e("Something can't move!", "Location:" + locationX + ","
						+ locationY);
			}
		}
	}

	public abstract void move();
}
