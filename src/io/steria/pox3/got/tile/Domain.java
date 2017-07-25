package io.steria.pox3.got.tile;

import java.util.Optional;
import java.util.Random;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.war.IArmy;

/////////////////////////////////////////////////////////////////////
public class Domain extends Tile {

	int population; // resource ?
	String name;

	Optional<House> house; // implique de vérifier à chaque fois si le domaine
							// possède une maison ou non

	static Random seed = new Random(); // on n'a pas besoin d'une seed par
										// Domain, d'autant plus que ça demande
										// de la puissance de calcul en plus.

	public Domain(int x, int y, String name) {
		super(x,y);
		this.population = seed.nextInt(10) + 1;
		this.name = name;
		// on force un nom dans le Domain et c'est la construction du Domain qui
		// va générer la population aléatoire
	}

	IArmy createArmy() {
		return null;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
