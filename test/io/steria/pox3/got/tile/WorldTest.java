package io.steria.pox3.got.tile;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.war.Direction;

public class WorldTest {

	World world;

	@Before
	public void setUp() throws Exception {
		world = new World();
	}

	@Test
	public void testGenerate() {
		world.generate();
		assertTrue(world.tiles.length == 12);
		assertTrue(world.tiles[0].length == 11);
	}

	@Test
	public void testAssignFreeDomain() {
		world.assignFreeDomain(1, 0, 4, 2, "North");
		assertTrue(world.tiles[1][0] instanceof Domain);
		Domain north1 = (Domain) world.tiles[1][0];
		assertTrue(north1.name.equals("North-1"));

		world.assignFreeDomain(8, 6, 2, 2, "Volantis");
		assertTrue(world.tiles[8][6] instanceof Domain);
		Domain volantis2 = (Domain) world.tiles[9][6]; // on teste la case (9;6)
														// pour v�rifier qu'on a
														// bien attribu� le
														// domaine � plus d'une
														// case
		assertTrue(volantis2.name.equals("Volantis-2"));

	}

	@Test
	public void testAssignDomainWithHouse() {

		House stark = new HouseFactory().getStark();

		world.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		assertTrue(world.tiles[1][2] instanceof Domain);
		Domain winterfell6 = (Domain) world.tiles[2][3];
		assertTrue(winterfell6.name.equals("Winterfell-6"));
		assertTrue(winterfell6.house.equals(Optional.of(stark)));

		House martell = new HouseFactory().getMartell();

		world.assignDomainWithHouse(3, 8, 2, 2, "Dorne", martell);
		assertTrue(world.tiles[3][8] instanceof Domain);
		Domain dorne3 = (Domain) world.tiles[3][9];
		assertTrue(dorne3.name.equals("Dorne-3"));
		assertTrue(dorne3.house.equals(Optional.of(martell)));

	}

	@Test
	public void testFillWater() {
		House stark = new HouseFactory().getStark();

		world.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		Domain winterfell6 = (Domain) world.tiles[2][3];
		world.fillWater();
		assertTrue(winterfell6.name.equals("Winterfell-6"));
		assertTrue(world.tiles[6][6] instanceof WaterTile);

	}

	@Test
	public void testAllowMove() {
		world.generate();
		WaterTile corner = (WaterTile) world.tiles[0][0];
		Tile calais = world.tiles[1][0];
		Tile dunkerque = world.tiles[2][0];
		
		assertTrue(world.allowMove(calais, corner, true));
		//on bouge de 1, dans l'eau, on a un bateau
		assertFalse(world.allowMove(calais, corner, false));
		//on bouge de 1, dans l'eau, on a pas de bateau
		assertFalse(world.allowMove(dunkerque, corner, true));
		//on bouge de 2, sur l'eau, on a un bateau		
		assertTrue(world.allowMove(calais, dunkerque, false));
		//on bouge de 1, sur terre
		assertTrue(world.allowMove(dunkerque, calais, false));
		//on bouge de 1, sur terre
		
	}

	@Test
	public void testIsWinter() {
		assertFalse(world.winter);
		world.startWinter();
		assertTrue(world.winter);
	}

	@Test
	public void testBasicDirection() {
		world.generate();
		Domain winterfell2 = (Domain) world.tiles[2][2];
		// new Domain(2, 2, "Winterfell-2");

		// X AXIS
		Tile tile = world.neighbour(winterfell2, Direction.EAST).get();
		assertTrue(tile.x == 3);
		assertTrue(tile.y == 2);

		tile = world.neighbour(winterfell2, Direction.WEST).get();
		assertTrue(tile.x == 1);
		assertTrue(tile.y == 2);

		// Y AXIS
		tile = world.neighbour(winterfell2, Direction.NORTH).get();
		assertTrue(tile.x == 2);
		assertTrue(tile.y == 1);

		tile = world.neighbour(winterfell2, Direction.SOUTH).get();
		assertTrue(tile.x == 2);
		assertTrue(tile.y == 3);
	}

	@Test
	public void testComplexDirection() {
		world.generate();
		WaterTile corner = (WaterTile) world.tiles[0][0];

		// X AXIS
		Optional<Tile> tile = world.neighbour(corner, Direction.WEST);
		assertFalse(tile.isPresent());

		tile = world.neighbour(corner, Direction.EAST);
		assertTrue(tile.isPresent());

		// Y AXIS
		tile = world.neighbour(corner, Direction.NORTH);
		assertFalse(tile.isPresent());

		tile = world.neighbour(corner, Direction.SOUTH);
		assertTrue(tile.isPresent());

	}
}
