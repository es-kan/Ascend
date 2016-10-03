package ascend;

public class Goblin extends Enemy {
	public Goblin(Game game) {
		super(game);
		attribute = 'G';
		attackPower = 1;
	}

	// do specific Goblin stuff. Like hitting a Hero in the face for a damage.
	public void act() {
		boolean acted = false;
		for (Tile t : game.getSurroundingSquare(currentPosition)) {
			if (t.containsHero()) {
				this.attack((Actor) t.unit);
				acted = true;
			}
		}
		if (!acted) {
			super.act();
		}
	}

	
}
