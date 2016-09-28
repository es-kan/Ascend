package ascend;

public class Hero extends Actor {
	public Hero(Game game) {
		super(game);
		attribute = 'H';
	}

	public void printPosition() {
		System.out.println(currentPosition.x + " " + currentPosition.y);
	}
}
