package uk.ac.brunel.cs1702.Cylons;

public class ResurrectionShip {

	// YOU CAN ADD FIELDS HERE

	public int shipNumber;
	public boolean infected = false;
	public int cylonPerShip = Constants.NUMBER_OF_CYLON_MODELS / Constants.MAX_NUMBER_OF_SHIPS;
	public int[] bodies = new int[cylonPerShip];

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected ResurrectionShip(int shipID) {

		if (shipID > 0 && shipID <= Constants.MAX_NUMBER_OF_SHIPS) {
			this.shipNumber = shipID;
			for (int i = 0; i < bodies.length; i++) {
				this.bodies[i] = Constants.MAX_NUMBER_OF_BODIES_PER_MODEL_IN_RESURRECTION_SHIP;
			}
		}

	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected int getBodyCount(int modelNo) {
		if ((modelNo - 1) < (this.getShipID() * cylonPerShip)
				&& (modelNo - 1) >= (this.getShipID() - 1) * cylonPerShip) {
			return bodies[(modelNo - 1) % cylonPerShip];
		} else {
			return 0;
		}
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public Cylon[][] getCylonArmy(int[] modelNoCount) throws CylonException {

		Cylon[][] army = new Cylon[modelNoCount.length][];
		for (int i = 0; i < modelNoCount.length; i++) {
			if (i < (this.getShipID() * cylonPerShip) && i >= (this.getShipID() - 1) * cylonPerShip) {
				if (modelNoCount[i] <= this.getBodyCount(i + 1)) {
					this.bodies[i % cylonPerShip] = bodies[i % cylonPerShip] - modelNoCount[i];
					army[i] = new Cylon[modelNoCount[i]];
					for (int j = 0; j < modelNoCount[i]; j++) {
						army[i][j] = new Cylon(i + 1);
					}
				} else {
					army[i] = new Cylon[0];
				}

			} else {
				army[i] = new Cylon[0];
			}
		}
		return army;
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected int resurrect(Cylon cylon) throws CylonException {
		if (cylon.isInfected() == false && getBodyCount(cylon.getModel()) > 0) {
			bodies[(cylon.getModel() - 1) % cylonPerShip] -= 1;
			cylon.resurrectionCount += 1;
			return cylon.getModel();
		} else {
			if (cylon.isInfected() == true) {
				for (int i = 0; i < bodies.length; i++) {
					this.bodies[i] = 0;
				}
			}
			throw new CylonException();
		}
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected int getShipID() {
		return this.shipNumber;
	}
}
