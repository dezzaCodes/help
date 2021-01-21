package unsw.dungeon;

public interface PlayerSubject {
	public void notifyEnemy();
	public void addEnemy(Enemy e);
	public void removeEnemy(Enemy e);
}
