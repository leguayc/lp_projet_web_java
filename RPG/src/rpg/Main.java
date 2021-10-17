package rpg;

import java.util.Scanner;

import rpg.map.Map;
import rpg.movable.destructibles.DestructibleFactory;
import rpg.movable.destructibles.characters.player.Player;

public class Main {

	public static void main(String[] args) {
		System.out.println("--------------------------------------------");
		System.out.println("\tWelcome to the freelands !");
		System.out.println("--------------------------------------------");
		System.out.println("\nYour journey will begin soon, but before that ...\n\n");
		Scanner sc = new Scanner(System.in);
		Player player = DestructibleFactory.createPlayer(sc);
		
		Map map = new Map();
		
		map.placeObject(player);
		
		Game.play(player, map, sc);
		
		sc.close();
	}

}
