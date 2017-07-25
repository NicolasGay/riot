package io.steria.pox3.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.game.Game;
import io.steria.pox3.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.tile.World;

public class GameTest {
	World world;
	Game game;
	Player anne;
	Player nicolas;
	House stark, lannister;

	@Before // sert � ce que cette fonction se lance syst�matiquement avant
			// chaque test de GameTest
	public void setUp() {
		System.out.println("reinitialize new game");
		world = new World();
		this.game = new Game(world); // le this sert � utiliser le "game" plac� hors
								// scope et pas un "game" �ventuellement plac�
								// comme param�tre de la fonction

		HouseFactory factory = new HouseFactory();
		this.stark = factory.getStark();
		// pas besoin de faire un new puisqu'on a cr�� l'objet au dessus
		this.lannister = factory.getLannister();

		this.anne = new Player(game,"Anne", lannister);
		this.nicolas = new Player(game,"Nicolas", stark);

	}

	// @Test
	// public void testChoosePlayerNumber() {
	// game.choosePlayerNumber(5);
	// assertTrue(game.players.size() == 5); // le assert vient de la
	// // biblioth�que JUnit, qui
	// // contient toutes les fonctions
	// // n�cessaires � la r�alisation
	// // de tests
	//
	// }
	// on a supprim� la fonction dans Game

	@Test
	public void testGetAvailableHouses() {
		assertEquals(8, game.getAvailableHouses().size());
		assertTrue(game.getAvailableHouses().size() == 8);	

	}

	@Test
	public void testSavePlayer() {
		assertTrue(game.getAvailableHouses().size() == 8);
		assertTrue(game.getAvailableHouses().size() == 8);
		anne.house = lannister;
		game.savePlayer(anne);
		assertTrue(game.getAvailableHouses().size() == 7);
	}

}
