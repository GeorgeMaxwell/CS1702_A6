package uk.ac.brunel.cs1702.Cylons;

public class Cylon {

	// YOU CAN ADD FIELDS HERE
	public int model;
	public int resurrectionCount;
	public boolean isDeadForever;
	public boolean isInfected;
	public int parentShip;
	public int cylonPerShip = Constants.NUMBER_OF_CYLON_MODELS / Constants.MAX_NUMBER_OF_SHIPS;

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	protected Cylon(int modelNo) throws CylonException {

		if (modelNo > Constants.NUMBER_OF_CYLON_MODELS || modelNo <= 0) {
			throw new CylonException();
		} else {
			this.model = modelNo;
			this.parentShip = (int) (this.model -1)/cylonPerShip;
		}
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public int getModel() {
		return this.model;
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public int getResurrectionCount() {
		return this.resurrectionCount;
	}
	


	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public boolean isDeadForever() {
		return this.isDeadForever;
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public void killed() throws CylonException {
		ResurrectionShip myShip = ResurrectionShipFactory.getInstance().findYourShip(this);
        try {
            myShip.resurrect(this);
        }
        catch(CylonException e){
            this.isDeadForever = true;
        }
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public void setInfected() {
		this.isInfected = true;
	}

	// IMPLEMENT THIS METHOD AND DO NOT MODIFY ITS SIGNATURE
	public boolean isInfected() {
		return this.isInfected;
	}
}
