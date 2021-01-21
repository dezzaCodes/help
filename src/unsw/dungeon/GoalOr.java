package unsw.dungeon;

import java.util.ArrayList;

public class GoalOr implements GoalComponent {

	private String goal;
	private boolean complete;
	private ArrayList<GoalComponent> children;

	/**
	 * creates an OR goal class
	 * @param goal is the name of the goal
	 */
	public GoalOr(String goal) {
		this.complete = false;
		this.goal = goal;
		this.children = new ArrayList<GoalComponent>();
	}

	public String getGoal() {
		for(GoalComponent g : children)
			g.goalName();
		return goal;
	}

	public boolean isComplete() {
		return complete;
	}

	@Override
	public boolean isGoalComplete() {
		for (GoalComponent g : children) {
			if (g.isGoalComplete())
				return true;
		}
		return false;
	}

	@Override
	public String goalName() {
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