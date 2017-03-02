package game;

import mapitems.AMapItem;

/**
 * @author Andrew
 * 
 *         This is a class used to hold the Grid and respawn location for each
 *         level & the ENUM for directions.
 */
public class AMap {
	public AMapItem[][] grid;
	public int gridSizeX;
	public int gridSizeY;
	public int respawnX;
	public int respawnY;

	public AMap(int x, int y, int rX, int rY) {
		gridSizeX = x;
		gridSizeY = y;
		respawnX = rX;
		respawnY = rY;
		grid = new AMapItem[gridSizeX][gridSizeY];
	}

	static public enum Direction {
		NORTH, EAST, SOUTH, WEST;
	};
}
