package unsw.dungeon;

public interface GoalComponent {
	/**
	 * checks whether the goal is complete
	 * if the goal is AND or OR, will return true if 
	 * the condition is met (AND everything is complete and 
	 * OR if one thing is complete)
	 * if the goal is a single, will return whether it is
	 * complete or not
	 * @return true or false depending on whether the goal has 
	 * been completed or not
	 */
	public boolean isGoalComplete();
	
	/**
	 * returns the goals name
	 * @return a string of the goals name
	 */
	public String goalName();
	
	/**
	 * updates the goal to whether it has been completed or not
	 * sets the complete field in the class
	 */
	public void updateComplete();
	
	/**
	 * allows us to add goals into AND and OR goals
	 * in single goals performs nothing
	 * @param g is the goal to add
	 */
	public void addGoal(GoalComponent g);
	
	/**
	 * returns the name of the goal
	 * @return a string of the goal field
	 */
	public String getGoals();
}
