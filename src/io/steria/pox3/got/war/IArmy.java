package io.steria.pox3.got.war;

import io.steria.pox3.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Tile;

public interface IArmy {

	int getTotalTroops();

	int getMovedTroops();

	int getReadyTroops();
	// On ne peut pas simplement créer un int troops parce que les interfaces,
	// comme elles sont abstraites, ne peuvent pas contenir autre chose que des
	// fonctions (on ne réserve pas de place en mémoire pour les abstract).

	boolean attack(IArmy ennemy);

	// On n'a pas besoin de coder à l'intérieur des fonctions car on est en
	// interface
	void move(int troops, Direction direction);

	void move(Direction direction); // pour déplacer toutes les troupes
	
	
	Tile getPosition();

	House getHouse();

	ArmyState getState();
	
	Player getPlayer();

}
