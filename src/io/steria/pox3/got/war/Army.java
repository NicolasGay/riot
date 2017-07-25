package io.steria.pox3.got.war;

import io.steria.pox3.game.Player;
import io.steria.pox3.game.PlayerRoundEndedException;
import io.steria.pox3.game.RoundState;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Domain;
import io.steria.pox3.got.tile.Tile;
import io.steria.pox3.got.tile.World;

public class Army implements IArmy {

	int readyTroops;
	int movedTroops;
	House house;
	Tile position;

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
		
		if(troops>this.readyTroops)
			throw new IllegalArgumentException();
		
		World world = this.getPlayer().getGame().getWorld();
		
		if(troops == this.readyTroops){
			//we move everything
			Tile destination =world.neighbour(this.getPosition(), direction).orElseThrow(()->new IllegalArgumentException());
			
			if(world.allowMove(this.position, destination, this.getHouse().hasBoat())){
				
				this.position = destination;
				this.getPlayer().decreaseMoves();
				this.readyTroops=0;
			}
			else
				throw new IllegalStateException("You don't have a boat !");
		}
		
		//TODO Nicolas GOT-2342 : case where we split Army
	}


	@Override
	public void move(Direction direction) {
		this.move(this.readyTroops, direction);
		
	}

}
