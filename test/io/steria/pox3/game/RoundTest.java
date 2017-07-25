package io.steria.pox3.game;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.tile.World;
import io.steria.pox3.got.war.Army;
import io.steria.pox3.got.war.Direction;
import io.steria.pox3.got.war.IArmy;

public class RoundTest {

	World world;
	Game game;
	Round round;
	Player a, b;
	IArmy a1, a2, a3, b1;
	House stark, lannister;

	@Before
	public void setUp() throws Exception {
		world = new World();
		world.generate();
		round = new Round();

		this.game = new Game(world);

		HouseFactory factory = new HouseFactory();
		this.stark = factory.getStark();
		this.lannister = factory.getLannister();
		this.a = new Player(game,"Anne", lannister);
		this.b = new Player(game,"Nicolas", stark);

		a1 = new Army(2, stark, world.getWinterfell7());
		a2 = new Army(2, stark, world.getMeereen3());
		a3 = new Army(2, stark, world.getThrone());
		b1 = new Army(2, stark, world.getTheEyrie1());
		
		game.players.add(this.a);
		game.players.add(this.b);
		
	}

	@Test(expected = PlayerRoundEndedException.class)
	public void testEndPlayer() {
		a1.move(Direction.NORTH);
		round.end(a);
		a2.move(Direction.SOUTH);
	}

}
