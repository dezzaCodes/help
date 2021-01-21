package unsw.dungeon;

public class GoalSingle implements GoalComponent {

	private String goal;
	private boolean complete;
	private Dungeon dungeon;
	
	/**
	 * creates a simple single goal
	 * @param dungeon - the dungeon that the goal belongs to
	 * @param goal - the name of the goal
	 */
	public GoalSingle(Dungeon dungeon, String goal) {
		this.dungeon = dungeon;
		this.goal = goal;
		this.complete = false;
	}
	
	@Override
	public boolean isGoalComplete() {
		return complete;
	}
	
	public void updateComplete() {
		this.complete = dungeon.checkGoals(goal);
	}

	@Override
	public String goalName() {
		return goal;
	}

	@Override
	public void addGoal(GoalComponent g) {
		return;
	}

	@Override
	public String getGoals() {
		return this.goal;
	}
}
