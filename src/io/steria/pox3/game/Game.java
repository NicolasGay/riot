package io.steria.pox3.game;

import java.util.ArrayList;
import java.util.List;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.tile.World;
import io.steria.pox3.objectives.ObjectiveCard;

/**
 * <strong>Starting</strong> class for launching game
 * 
 * @author AELION
 *
 */

/////////////////////////////////////////////////////////////////////
public class Game {
	
	World world;
	List<Player> players = new ArrayList<>();
	List<House> houses = new HouseFactory().getAllHouses();
	List<House> availableHouses = new HouseFactory().getAllHouses();
	// Au début du toutes les House sont diponibles.
	// Au fur et à mesure qu'elles sont attribuées au Player,
	// les autres Player ont moins de choix de House possible

	
	
	
	List<ObjectiveCard> cards;

	public Game(World world) {
		this.world = world;
	}
	

	public World getWorld() {
		return world;
	}


	List<Round> rounds = new ArrayList<>();

	public void init() {
		newRound();
	}

	// void choosePlayerNumber(int number) {
	//
	// this.players = new ArrayList<>();
	// // en JAVA on n'a pas besoin de
	// // préciser la taille du tableau
	// // quand on le déclare
	//
	// for (int i = 0; i < number; i++) {
	// this.players.add(new Player());
	// // on remplit le tableau avec le
	// // number de Player
	// }
	//
	// }
	// on vire la fonction parce qu'on a ajouté un constructeur dans Player

	List<House> getAvailableHouses() {

		return availableHouses;
	}

	void savePlayer(Player player) {

		this.players.add(player);
		this.availableHouses.remove(player.house);
	}

	Round getCurrentRound() {

		return this.rounds.get(this.rounds.size() - 1);
		// on n'oublie pas que l'indexation commence à 0 !
		// on n'oublie pas de se protéger contre le cas où la size est à 0 !
	}

	void newRound() {
		Round newRound = new Round();
		this.rounds.add(newRound);

		boolean firstNotDead = true;

		for (Player player : this.players) {
			if (player.roundState != RoundState.DEAD) {
				player.moves = 3;
				if (firstNotDead) {
					player.roundState = RoundState.PLAYING;
					firstNotDead = false;
				} else {
					player.roundState = RoundState.WAITING;
				}
			} else {
				player.moves = 0;
			}

		}
	}

}
