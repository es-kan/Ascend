package ascend;

public abstract class Actor extends Unit {
	//ATT: only one per tile.
	//actor = all enemies + hero
	public Actor(Game game){
		super(game);
		comparisonInteger = 1000;
	}
}


