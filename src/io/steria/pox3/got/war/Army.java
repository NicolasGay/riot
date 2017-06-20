package io.steria.pox3.got.war;

import io.steria.pox3.game.Player;
import io.steria.pox3.game.PlayerRoundEndedException;
import io.steria.pox3.game.RoundState;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Domain;
import io.steria.pox3.got.tile.Tile;

public class Army implements IArmy {

	int readyTroops;
	int movedTroops;
	House house;
	Domain position;

	public Army(int troops, House house, Domain position) {
		this.readyTroops = troops;
		this.house = house;
		this.position = position; // on met position dans le constructeur de
									// Army car même au démarrage du jeu il faut
									// une position initiale
	}
	
	
	@Override
	public Player getPlayer() {
		return this.house.getPlayer();
	}


	@Override
	public boolean attack(IArmy ennemy) {
		
		return false;
	}


	@Override
	public Tile getPosition() {
		return this.position;
	}

	@Override
	public House getHouse() {
		return this.house;
	}

	@Override
	public ArmyState getState() {
		return ArmyState.IDLE;
	}


	@Override
	public int getTotalTroops() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getMovedTroops() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getReadyTroops() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void move(int troops, Direction direction) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void move(Direction direction) {
		this.getPlayer().decreaseMoves();
		
	}

}
