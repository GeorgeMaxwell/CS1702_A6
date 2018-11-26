package uk.ac.brunel.cs1702;

import java.util.ArrayList;

import javax.swing.text.Document;

import uk.ac.brunel.cs1702.Cylons.*;

public class CylonArmy {

	// YOU CAN ADD FIELDS HERE
	public int cylonPerShip = Constants.NUMBER_OF_CYLON_MODELS / Constants.MAX_NUMBER_OF_SHIPS;
public ArrayList<Cylon[][]> armyList = new ArrayList<Cylon[][]>();
	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public CylonArmy(int[] modelNoCount) throws CylonException {
		
		
		for (int i = 0; i< modelNoCount.length; i++) {
			if (modelNoCount[i]<0 || modelNoCount[i] >Constants.MAX_NUMBER_OF_BODIES_PER_MODEL_IN_RESURRECTION_SHIP) {
				throw new CylonException();
			}
		}		
		if (modelNoCount.length != Constants.NUMBER_OF_CYLON_MODELS) {
			throw new CylonException();
		} else {
			for (int i = 0; i < Constants.MAX_NUMBER_OF_SHIPS; i++) {
				ResurrectionShip myShip = ResurrectionShipFactory.getInstance().getNewShip(i + 1);
				armyList.add(myShip.getCylonArmy(modelNoCount));
			}
		}		
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public Cylon[][] getArmy() {
		Cylon [][] returnArray = new Cylon [Constants.NUMBER_OF_CYLON_MODELS][];
		for (int i = 0; i < armyList.size(); i++) {
			for (int j = 0; j < armyList.get(i).length; j++) {
				if ((j<((i+1) * cylonPerShip)&&j>= i * cylonPerShip)) {
					returnArray[j] = new Cylon[armyList.get(i)[j].length];
					for (int k = 0; k < armyList.get(i)[j].length; k++) {
						returnArray[j][k] = armyList.get(i)[j][k];
					}
				}
			}
		}
		
		return returnArray;
	}
}
