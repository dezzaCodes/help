package unsw.dungeon;

/**
 * Inactive state for the plate
 * @author jamespo
 * implements the platestate interface
 */
public class PlateInactive implements PlateState {

	/**
	 * Returns an instance of an active plate state
	 */
	@Override
	public PlateState Active() {
		return new PlateActive();
	}

	/**
	 * Returns itself since it is already inactive
	 */
	@Override
	public PlateState Inactive() {
		return this;
	}

}
