package io.steria.pox3.got.tile;

import java.util.Optional;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.war.Direction;

public class World {

	Tile[][] tiles = new Tile[12][11];
	boolean winter = false;
	int winterLatitude = 0;

	public World() {

	}

	public void generate() {
		// on a mis generate() public pour pouvoir l'utiliser depuis un autre
		// package (notamment dans IArmyTest)

		assignFreeDomain(1, 0, 4, 2, "North");
		assignFreeDomain(8, 2, 2, 2, "Braavos");
		assignFreeDomain(8, 4, 2, 2, "Pentos");
		assignFreeDomain(8, 6, 2, 2, "Volantis");
		assignFreeDomain(10, 4, 1, 6, "Red Waste");

		House stark = new HouseFactory().getStark();
		House lannister = new HouseFactory().getLannister();
		House baratheon = new HouseFactory().getBaratheon();
		House martell = new HouseFactory().getMartell();
		House tyrell = new HouseFactory().getTyrell();
		House greyjoy = new HouseFactory().getGreyjoy();
		House arryn = new HouseFactory().getArryn();
		House targaryen = new HouseFactory().getTargaryen();

		assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		assignDomainWithHouse(1, 6, 2, 2, "Casterly Rock", lannister);
		assignDomainWithHouse(3, 6, 2, 2, "Kings Landing", baratheon);
		assignDomainWithHouse(3, 8, 2, 2, "Dorne", martell);
		assignDomainWithHouse(1, 8, 2, 2, "High Garden", tyrell);
		assignDomainWithHouse(1, 4, 2, 2, "Iron Islands", greyjoy);
		assignDomainWithHouse(3, 4, 2, 2, "The Eyrie", arryn);
		assignDomainWithHouse(8, 8, 2, 2, "Meereen", targaryen);

		fillWater();
	}

	void assignDomainWithHouse(int xOrigin, int yOrigin, int xSize, int ySize, String name, House house) {

		int number = 1;

		for (int y = yOrigin; y < yOrigin + ySize; y++) {
			for (int x = xOrigin; x < xOrigin + xSize; x++) {

				Domain domain = new Domain(x, y, name + "-" + number);
				domain.house = house == null ? Optional.empty() : Optional.of(house);
				tiles[x][y] = domain;
				number++;
			}
		}

	}

	void assignFreeDomain(int xOrigin, int yOrigin, int xSize, int ySize, String name) {
		assignDomainWithHouse(xOrigin, yOrigin, xSize, ySize, name, null);
	}

	void fillWater() {

		for (int x = 0; x <= 11; x++) {
			for (int y = 0; y <= 10; y++) {

				if (this.tiles[x][y] == null) {
					WaterTile water = new WaterTile(x, y);
					this.tiles[x][y] = water;
				}
			}
		}
	}

	public boolean allowMove(Tile origin, Tile destination, boolean hasBoat) {

		if (destination.x > origin.x + 1 || destination.y > origin.y + 1 || destination.x < origin.x-1 || destination.y < origin.y-1)
			return false;
		else if (destination instanceof WaterTile && !hasBoat)
			return hasBoat;

	return true;

	}

	boolean isWinter() {
		return false;
	}

	void startWinter() {
		this.winter = true;
	}

	public void display() {

		for (int y = 0; y <= 10; y++) {
			for (int x = 0; x <= 11; x++) {
				Tile tile = this.tiles[x][y];

				if (tile.toString().length() > 10) {
					System.out.print(" " + tile.toString().substring(0, 10) + "\t");
				} else {
					System.out.print(" " + tile + "\t");
				}

			}
			System.out.println();
		}
	}

	public Optional<Tile> neighbour(Tile tile, Direction direction) {

		int x = tile.x;
		int y = tile.y;

		switch (direction) {
		case NORTH:
			y--;
			break;
		case SOUTH:
			y++;
			break;
		case EAST:
			x++;
			break;
		case WEST:
			x--;
			break;
		default:
			throw new IllegalArgumentException();
		}

		if (x < 0 || x >= this.tiles.length || y < 0 || y >= this.tiles[0].length)
			return Optional.empty();
		else {
			Tile neighbour = this.tiles[x][y];
			return Optional.of(neighbour);
		}
	}

	public static void main(String[] args) {
		World map = new World();
		map.generate();
		map.display();
	}

	public Tile get(int x, int y) {
		return this.tiles[x][y];
	}

	public Domain getWinterfell7() {
		return (Domain) this.get(3, 3);
	}

	public Domain getTheEyrie1() {
		return (Domain) this.get(3, 4);
	}

	public Domain getThrone() {
		return (Domain) this.get(4, 7);
	}

	public Domain getMeereen3() {
		return (Domain) this.get(8, 9);
	}

}
