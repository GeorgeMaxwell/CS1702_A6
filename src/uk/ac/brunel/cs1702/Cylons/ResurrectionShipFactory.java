package uk.ac.brunel.cs1702.Cylons;

import java.util.ArrayList;

//DO NOT MODIFY THIS LINE
public class ResurrectionShipFactory implements ShipFinder {
	
	public ArrayList<ResurrectionShip> ships = new ArrayList<ResurrectionShip>();	
	//public ResurrectionShip [] ships = new ResurrectionShip [Constants.MAX_NUMBER_OF_SHIPS];
	//YOU CAN ADD FIELDS HERE
	
	//DO NOT MODIFY THIS LINE
	private static ResurrectionShipFactory instance;

	//DO NOT MODIFY THIS CONSTRUCTOR
	private ResurrectionShipFactory(){}
	
	//DO NOT MODIFY THIS METHOD
	public static ResurrectionShipFactory getInstance(){
		if(instance == null)
			instance = new ResurrectionShipFactory();
		return instance;
	}

	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public ResurrectionShip getNewShip(int shipID) throws CylonException {
		if (shipID<1 || shipID>Constants.MAX_NUMBER_OF_SHIPS) {
			throw new CylonException();
		}
		else{
			for (int i = 0; i <ships.size(); i++) {
				if (ships.get(i).getShipID() == shipID) {
					throw new CylonException();
				}
			}
			ResurrectionShip newShip = new ResurrectionShip(shipID);
			ships.add(newShip);
			return newShip;
		}
	}
	
	//IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	@Override
	public ResurrectionShip findYourShip(Cylon cylon) {//ships.get(rightShip);
		int cylonPerShip = Constants.NUMBER_OF_CYLON_MODELS/Constants.MAX_NUMBER_OF_SHIPS;
		int rightShip = (cylon.getModel() -1)/cylonPerShip;
		
		return ships.get(rightShip);
	}
}