package unsw.dungeon;

import java.util.ArrayList;

public class GoalAnd implements GoalComponent {
	private String goal;
	private boolean complete;
	private ArrayList<GoalComponent> children;

	/**
	 * creates a goal AND class
	 * @param goal - the name of the goal
	 */
	public GoalAnd(String goal) {
		this.goal = goal;
		this.complete = false;
		this.children = new ArrayList<GoalComponent>();
	}

	public boolean isComplete() {
		return complete;
	}
	
	@Override
	public boolean isGoalComplete() {
		for (GoalComponent g : children) {
			if (!g.isGoalComplete()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String goalName() {
		for(GoalComponent g : children)
			g.goalName();
		return goal;
	}

	@Override
	public void updateComplete() {
		for (GoalComponent g : children) {
			g.updateComplete();
		}
		this.complete = isGoalComplete();
	}

	@Override
	public void addGoal(GoalComponent g) {
		children.add(g);
	}

	@Override
	public String getGoals() {
		for (GoalComponent g : children) {
			g.getGoals();
		}
		return this.goal;
	}
}
