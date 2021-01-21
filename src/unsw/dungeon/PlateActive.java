package unsw.dungeon;
/**
 * Active state for the plate
 * @author jamespo
 * implements the platestate interface
 */
public class PlateActive implements PlateState {

	/**
	 * Returns an instance of an active plate state, returns itself since it is
	 * active
	 */
	@Override
	public PlateState Active() {
		return this;
	}

	/**
	 * Returns an instance of an inactive plate state
	 */
	@Override
	public PlateState Inactive() {
		return new PlateInactive();
	}

}
