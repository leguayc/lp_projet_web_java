package rpg.map;

import java.util.Scanner;

import rpg.movable.FinishLine;
import rpg.movable.Movable;
import rpg.movable.destructibles.DestructibleFactory;
import rpg.utils.DiceResult;

public class Map {
	private static final int MAPSIZE = 10;
	private static final String[] CARDINALS = {"north", "south", "east", "west"};
	private Movable[][] map;
	
	public Map() {
		this.map = new Movable[MAPSIZE][MAPSIZE];
		this.initMap();
	}
	
	/**
	 * Init map with random stuff (and finish line)
	 */
	public void initMap() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if (DiceResult.rollDice(6)%2 == 0) {
					this.map[i][j] = null;
				} else {
					this.map[i][j] = DestructibleFactory.createRandomDestructible(i, j);
				}
			}
		}
		
		// We need this two places to be empty
		this.map[0][0] = null; // Player start
		this.map[MAPSIZE-1][MAPSIZE-1] = null; // Finish line
		
		FinishLine f = new FinishLine(MAPSIZE-1, MAPSIZE-1);
		this.placeObject(f);
	}
	
	/**
	 * Check if move is valid
	 * @param m Movable that will move
	 * @param direction Direction in which movable moves
	 * @return Is move valid
	 */
	public static boolean isValidMove(Movable m, String direction) {
		int x = m.getX();
		int y = m.getY();
		
		switch(direction) {
			case "north" :
				x--;
				break;
			case "south" :
				x++;
				break;
			case "west" :
				y--;
				break;
			case "east" :
				y++;
				break;
			default :
				return false;
		}
		
		if ((x >= 0 && x < MAPSIZE) && (y >= 0 && y < MAPSIZE)) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Display all valid moves in the position of the movable m
	 * @param m
	 */
	public void displayValidMoves(Movable m) {
		String str = "You can move :";
		
		for(int i = 0; i < CARDINALS.length; i++) {
			if ( Map.isValidMove(m, CARDINALS[i]) ) {
				str += "\n\t- "+ CARDINALS[i];
			}
		}
		
		System.out.println(str);
	}
	
	/**
	 * Move m to its new position and clear its old coords, if there is something on the new coords, you do stuff accordingly
	 * @param m Movable
	 * @param oldCoords Old coords of Movable
	 * @param sc Scanner
	 */
	public void moveObject(Movable m, Point oldCoords, Scanner sc) {
		if ( this.map[m.getX()][m.getY()] == null ) {
			this.map[oldCoords.x][oldCoords.y] = null;
			this.map[m.getX()][m.getY()] = m;
		} else {
			if (this.map[m.getX()][m.getY()].moveOnto(m, sc)) {
				this.map[oldCoords.x][oldCoords.y] = null;
				this.map[m.getX()][m.getY()] = m;
			} else {
				m.setCoords(oldCoords.x,  oldCoords.y);
			}
		}
	}
	
	/**
	 * Place Movable on map
	 * @param m Movable
	 */
	public void placeObject(Movable m) {
		if ( this.map[m.getX()][m.getY()] == null) {
			this.map[m.getX()][m.getY()] = m;
		}
	}
	
	@Override
	public String toString() {
		String str = "* * * * * * * * * * * *";
		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if ( j == 0 ) {
					str += "\n*";
				}
				
				if ( this.map[i][j] != null ) {
					str += " " + this.map[i][j].toMapSymbol();
				} else {
					str += "  ";
				}
				
				if ( j == map.length-1 ) {
					str += " *";
				}
			}
		}
		
		str += "\n* * * * * * * * * * * *";
		
		return str;
	}
	
}
