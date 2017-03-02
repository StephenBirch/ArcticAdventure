package mapitems;

import game.GameActivity;

public class APenguin extends AMoveable {
	boolean movingLeft = false;
	public boolean moveLeftNext = false;

	public APenguin(GameActivity m, int x, int y) {
		super(m);
		locationX = x;
		locationY = y;
	}

	public void move() {
		if (locationX == 9) {
			movingLeft = true;
		} else if (locationX == 0) {
			movingLeft = false;
		} else if ((!(movingLeft))
				&& (main.map.grid[locationX + 1][locationY] == main.player)) {
			main.lifeLost();
		} else if ((movingLeft)
				&& (main.map.grid[locationX - 1][locationY] == main.player)) {
			main.lifeLost();
		} else if ((movingLeft)
				&& (main.map.grid[locationX - 1][locationY] != null)) {
			movingLeft = false;
		} else if ((!(movingLeft))
				&& (main.map.grid[locationX + 1][locationY] != null)) {
			movingLeft = true;
		}
		if (movingLeft) {
			moveWest();
			if (locationX <= 0) {
				moveLeftNext = false;
			} else if ((main.map.grid[locationX - 1][locationY] ==  (null))
					|| (main.map.grid[locationX - 1][locationY].getClass()
							.equals(APlayer.class))) {
				moveLeftNext = true;
			} else {
				moveLeftNext = false;
			}
		} else {
			moveEast();
			if (locationX >= 9) {
				moveLeftNext = true;
			} else if ((main.map.grid[locationX + 1][locationY] == (null))
					|| (main.map.grid[locationX + 1][locationY].getClass()
							.equals(APlayer.class))) {
				moveLeftNext = false;
			} else {
				moveLeftNext = true;
			}
		}
	}
}
