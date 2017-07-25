package io.steria.pox3.game;

import io.steria.pox3.got.story.House;
import io.steria.pox3.objectives.ObjectiveCard;

/////////////////////////////////////////////////////////////////////
public class Player {

	String name;
	House house;
	ObjectiveCard card;

	PlayerState state = PlayerState.PLAYING;
	RoundState roundState=RoundState.WAITING;

	public int moves = 3;

	// bidirectional relationship : argh
	Game game;
	Round currentRound;

	public Player(Game game, String name, House house) {
		this.game=game;
		this.name = name;
		this.house = house;
		// bidrirection argh
		this.house.setPlayer(this);
		// but this link never moves...so not so bad after all ! ;-)
	}

	boolean chooseName(String pName) { // On rajoute un p pour identifier que
										// c'est le name placé en paramètre

		this.name = pName; // Le this permet d'utiliser le name hors scope
		return true;
	}

	boolean chooseHouse(House pHouse) {

		this.house = pHouse;
		return true;
	}

	ObjectiveCard selectCard() {

		return null;
	}

	public void decreaseMoves() {
		if (this.roundState == RoundState.WAITING) {
			throw new PlayerRoundEndedException();
		}
		this.moves--;

	}

	public Game getGame() {
		return game;
	}
	
	

}
