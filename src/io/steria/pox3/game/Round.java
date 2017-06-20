package io.steria.pox3.game;

public class Round {
	
	
	void end(Player player){
		player.moves=0;
		player.roundState=RoundState.WAITING;
		
	}
	
	void end(){
		
	}
	
	
	
}
