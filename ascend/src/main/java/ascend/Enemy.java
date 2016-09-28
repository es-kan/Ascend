package ascend;

public abstract class Enemy extends Actor {
	abstract void act();

	public Enemy(Game game) {
		super(game);
		attribute = 'E';
	}
}
