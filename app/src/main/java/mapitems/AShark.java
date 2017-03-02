package mapitems;

import game.GameActivity;

public class AShark extends AMoveable {
	int counter = -1;
	public int x = 0;
	public int y = -1;

	public AShark(GameActivity m, int x, int y) {
		super(m);
		locationX = x;
		locationY = y;
	}

	public void move() {
		switch (++counter % 8) {
		case 0:
			moveNorth();
			break;
		case 1:
			moveNorth();
			x = 1;
			y = 0;
			break;
		case 2:
			moveEast();
			break;
		case 3:
			moveEast();
			x = 0;
			y = 1;
			break;
		case 4:
			moveSouth();
			break;
		case 5:
			moveSouth();
			x = -1;
			y = 0;
			break;
		case 6:
			moveWest();
			break;
		case 7:
			moveWest();
			x = 0;
			y = -1;
			break;
		default:
			moveNorth();
			break;
		}
	}
}
