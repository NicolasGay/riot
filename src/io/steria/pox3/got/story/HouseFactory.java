package io.steria.pox3.got.story;

import java.util.ArrayList;
import java.util.List;

public class HouseFactory {

	// On veut que AllHouse soit visible en dehors de son package "story"
	// On voudrait utiliser "public class" mais on n'est pas autorisé à utiliser
	// plus d'un public class par classe
	// On voudrait pourrait créer une nouvelle classe (nouveau fichier) pour
	// pouvoir utiliser "public class"

	private static List<House> houses = new ArrayList<>();
	// on met private pour être sûr qu'aucune class du package
	// ne puisse modifier houses

	public List<House> getAllHouses() {
		if (houses.size() == 8) {
			return houses;
			// si le tableau est déjà rempli, on ne re-crée pas une liste de
			// House
		}

		House stark = new House("Stark", "Winter is coming");
		House lannister = new House("Lannister", "Hear me roar");
		House baratheon = new House("Baratheon", "Ours is the fury");
		House martell = new House("Martell", "Unbowed, Unbent, Unbroken");
		House tyrell = new House("Tyrell", "Growing Strong");
		House greyjoy = new House("Greyjoy", "We do not sow");
		House arryn = new House("Arryn", "As high as honor");
		House targaryen = new House("Targaryen", "Fire and Blood");
		// pour pouvoir utiliser cette syntaxe, il faut passer
		// par un "constructeur" dans House

		houses.add(stark);
		houses.add(lannister);
		houses.add(baratheon);
		houses.add(martell);
		houses.add(tyrell);
		houses.add(greyjoy);
		houses.add(arryn);
		houses.add(targaryen);

		return houses;

	}

	public House getStark() {
		return getAllHouses().get(0);
	}

	public House getLannister() {
		return getAllHouses().get(1);
	}

	public House getBaratheon() {
		return getAllHouses().get(2);
	}

	public House getMartell() {
		return getAllHouses().get(3);
	}

	public House getTyrell() {
		return getAllHouses().get(4);
	}

	public House getGreyjoy() {
		return getAllHouses().get(5);
	}

	public House getArryn() {
		return getAllHouses().get(6);
	}

	public House getTargaryen() {
		return getAllHouses().get(7);
	}

}
