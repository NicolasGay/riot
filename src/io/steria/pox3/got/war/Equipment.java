package io.steria.pox3.got.war;

public interface Equipment {			// On ne sait pas, au moment de créer la classe, ce qu'il y aura dans l'équipement
										// On pourrait créer une class "abstract", on va plutôt choisir un type "interface"
										// qui est forcément abstrait
	
	void use();
}
