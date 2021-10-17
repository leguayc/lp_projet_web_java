package rpg.movable;

import java.util.Scanner;

import rpg.map.Map;
import rpg.map.Point;

public abstract class Movable {
	private Point point;
	
	public Movable() {
		point = new Point(0, 0);
	}
	
	public Movable(int x, int y) {
		point = new Point(x, y);
	}
	
	public int getX() {
		return this.point.x;
	}
	
	public int getY() {
		return this.point.y;
	}
	
	/**
	 * Move on map to the direction
	 * @param map Map
	 * @param direction Direction in which it moves
	 * @param sc Passed to parameters to avoid errors
	 */
	public void moveTo(Map map, String direction, Scanner sc) {
		if (Map.isValidMove(this, direction)) {
			Point old = new Point(this.point.x, this.point.y);
			
			switch(direction) {
				case "north" :
					this.point.x--;
					break;
				case "south" :
					this.point.x++;
					break;
				case "west" :
					this.point.y--;
					break;
				case "east" :
					this.point.y++;
					break;
			}
			
			map.moveObject(this, old, sc);
		}
	}
	
	/**
	 * Move to coords on map
	 * 
	 * @param map Map
	 * @param x CoordsX
	 * @param y CoordsY
	 * @param sc Scanner
	 */
	public void setCoords(int x, int y) {
		this.point.x = x;
		this.point.y = y;
	}
	
	/**
	 * Turn object to symbol on map
	 * 
	 * @return map symbol of object
	 */
	public abstract String toMapSymbol();
	
	/**
	 * Do stuff when something tries to move onto you
	 * 
	 * @param source
	 * @param sc
	 * @return result
	 */
	public abstract boolean moveOnto(Movable source, Scanner sc);
}
